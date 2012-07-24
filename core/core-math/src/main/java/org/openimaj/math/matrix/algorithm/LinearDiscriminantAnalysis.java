package org.openimaj.math.matrix.algorithm;

import java.util.List;

import org.openimaj.math.matrix.GeneralisedEigenvalueProblem;
import org.openimaj.math.matrix.MatrixUtils;
import org.openimaj.util.array.ArrayUtils;
import org.openimaj.util.pair.IndependentPair;

import Jama.Matrix;

/**
 * Implementation of Multiclass Linear Discriminant Analysis.
 * 
 * @author Jonathon Hare (jsh2@ecs.soton.ac.uk)
 *
 */
public class LinearDiscriminantAnalysis {
	private static class MeanData {
		double[] overallMean;
		double[][] classMeans;
		int numInstances;
	}
	
	
	protected int numComponents;
	protected Matrix eigenvectors;
	protected double[] eigenvalues;
	protected double[] mean;
	
	/**
	 * Construct with the given number of components.
	 * @param numComponents the number of components.
	 */
	public LinearDiscriminantAnalysis(int numComponents) {
		this.numComponents = numComponents;
	}
	
	private MeanData computeMeans(List<double[][]> data) {
		final int cols = data.get(0)[0].length;
		final int numClasses = data.size();
		
		MeanData md = new MeanData();
		md.overallMean = new double[cols];
		md.classMeans = new double[numClasses][];
		md.numInstances = 0;
		
		for (int i=0; i<numClasses; i++) {
			final double[][] classData = data.get(i);
			final int classSize = classData.length;
			
			md.classMeans[i] = computeSum(classData);
			md.numInstances += classSize;
			
			for (int j=0; j<cols; j++) {
				md.overallMean[j] += md.classMeans[i][j];
				md.classMeans[i][j] /= classSize;
			}
		}
		
		for (int i=0; i<cols; i++) {
			md.overallMean[i] /= (double)md.numInstances;
		}
		
		return md;
	}
	
	private double[] computeSum(double[][] data) {
		double[] sum = new double[data[0].length];
		
		for (int j=0; j<data.length; j++) {
			for (int i=0; i<sum.length; i++) {
				sum[i] += data[j][i];
			}
		}
		
		return sum;
	}
	
	/**
	 * Learn the LDA basis.
	 * @param data data grouped by class
	 */
	public void learnBasis(List<double[][]> data) {
		int c = data.size();
		
		if (c < 0 || numComponents >= c) 
			numComponents = c - 1;
		
		MeanData meanData = computeMeans(data);
		mean = meanData.overallMean;
		final double[][] classMeans = meanData.classMeans;
		
		final Matrix Sw = new Matrix(mean.length, mean.length);
		final Matrix Sb = new Matrix(mean.length, mean.length);
		
		for (int i=0; i<c; i++) {
			final Matrix classData = new Matrix(data.get(i));
			final double[] classMean = classMeans[i];
			
			Matrix zeroCentred = MatrixUtils.minusRow(classData, classMean);
			MatrixUtils.plusEquals(Sw, zeroCentred.transpose().times(zeroCentred));
			
			ArrayUtils.subtract(classMean, mean);
			Matrix diff = new Matrix(new double[][]{ classMean });
			MatrixUtils.plusEquals(Sb, MatrixUtils.times(diff.transpose().times(diff), meanData.numInstances));
		}
		
		IndependentPair<Matrix, double[]> evs = GeneralisedEigenvalueProblem.symmetricGeneralisedEigenvectorsSorted(Sb, Sw, numComponents);
		this.eigenvectors = evs.firstObject();
		this.eigenvalues = evs.secondObject();
	}
	
	/**
	 * Get the basis (the LDA eigenvectors)
	 * 
	 * @return the eigenvectors
	 */
	public Matrix getBasis() {
		return eigenvectors;
	}
	
	/**
	 * Get a specific basis vector as
	 * a double array. The returned array contains a
	 * copy of the data.
	 * 
	 * @param index the index of the vector
	 * 
	 * @return the eigenvector
	 */
	public double[] getBasisVector(int index) {
		double[] pc = new double[eigenvectors.getRowDimension()];
		double[][] data = eigenvectors.getArray();
		
		for (int r=0; r<pc.length; r++)
			pc[r] = data[r][index];
		
		return pc;
	}
	
	/**
	 * Get the basis eigenvectors. Each of column vector of the returned 
	 * matrix is an eigenvector.
	 * 
	 * Syntactic sugar for {@link #getBasis()}
	 * @return the eigenvectors
	 */
	public Matrix getEigenVectors() {
		return eigenvectors;
	}
	
	/**
	 * @return the eigen values corresponding to the principal components
	 */
	public double [] getEigenValues() {
		return eigenvalues;
	}
	
	/**
	 * Get the eigen value corresponding to the ith principal component.
	 * @param i the index of the component
	 * @return the eigen value corresponding to the principal component
	 */
	public double getEigenValue(int i) {
		return eigenvalues[i];
	}
	
	/**
	 * @return The mean values
	 */
	public double[] getMean() {
		return mean;
	}
	
	/**
	 * Generate a new "observation" as a linear combination of
	 * the eigenvectors (ev): mean + ev * scaling.
	 * <p>
	 * If the scaling vector is shorter than the number of
	 * components, it will be zero-padded. If it is longer,
	 * it will be truncated.
	 * 
	 * @param scalings the weighting for each eigenvector
	 * @return generated observation
	 */
	public double [] generate(double[] scalings) {
		Matrix scale = new Matrix(this.eigenvalues.length, 1);
		
		for (int i=0; i<Math.min(eigenvalues.length, scalings.length); i++)
			scale.set(i, 0, scalings[i]);
		
		Matrix meanMatrix = new Matrix(new double[][]{mean}).transpose();
		
		return meanMatrix.plus(eigenvectors.times(scale)).getColumnPackedCopy();
	}
	
	/**
	 * Project a matrix of row vectors by the basis. 
	 * The vectors are normalised by subtracting the mean and
	 * then multiplied by the basis. The returned matrix
	 * has a row for each vector. 
	 * @param m the vector to project
	 * @return projected vectors
	 */
	public Matrix project(Matrix m) {
		Matrix vec = m.copy();
		
		final int rows = vec.getRowDimension();
		final int cols = vec.getColumnDimension();
		final double[][] vecarr = vec.getArray();
		
		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++)
				vecarr[r][c] -= mean[c];
		
		//T = (Vt.Dt)^T == Dt.Vt
		return vec.times(eigenvectors);
	}
	
	/**
	 * Project a vector by the basis. The vector
	 * is normalised by subtracting the mean and
	 * then multiplied by the basis.
	 * @param vector the vector to project
	 * @return projected vector
	 */
	public double[] project(double [] vector) {
		Matrix vec = new Matrix(1, vector.length);
		final double[][] vecarr = vec.getArray();
		
		for (int i=0; i<vector.length; i++)
			vecarr[0][i] = vector[i] - mean[i];
		
		return vec.times(eigenvectors).getColumnPackedCopy();
	}
}

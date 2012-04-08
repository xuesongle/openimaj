/**
 * Copyright (c) 2011, The University of Southampton and the individual contributors.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *   * 	Redistributions of source code must retain the above copyright notice,
 * 	this list of conditions and the following disclaimer.
 *
 *   *	Redistributions in binary form must reproduce the above copyright notice,
 * 	this list of conditions and the following disclaimer in the documentation
 * 	and/or other materials provided with the distribution.
 *
 *   *	Neither the name of the University of Southampton nor the names of its
 * 	contributors may be used to endorse or promote products derived from this
 * 	software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.openimaj.experiment.evaluation.retrieval;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openimaj.experiment.dataset.Identifiable;
import org.openimaj.experiment.evaluation.AnalysisResult;

/**
 * A {@link RetrievalAnalyser} is used to analyse the raw search
 * results from a {@link RetrievalEngine} in the context of
 * a {@link RetrievalEvaluator} and to produce an {@link AnalysisResult}
 * describing the performance of the {@link RetrievalEngine}.
 * 
 * @author Jonathon Hare <jsh2@ecs.soton.ac.uk>
 *
 * @param <R> the type of {@link AnalysisResult} produced. 
 * @param <Q> the type of the queries
 * @param <D> the type of the documents
 */
public interface RetrievalAnalyser<R extends AnalysisResult, Q, D extends Identifiable> {
	/**
	 * Analyse ranked results from a {@link RetrievalEngine} against
	 * a ground-truth set of relevant results and produce an {@link AnalysisResult}
	 * which can be read by a human.
	 * 
	 * @param results the results per query from the {@link RetrievalEngine}.
	 * @param relevant the ground-truth relevant documents per query.
	 * @return the results of the analysis
	 */
	public R analyse(Map<Q, List<D>> results, Map<Q, Set<D>> relevant);
}
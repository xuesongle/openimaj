package org.openimaj.hardware.kinect.freenect;

import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * <i>native declaration : /usr/include/stdint.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@SuppressWarnings("all")
@Library("freenect-combined") 
public class freenect_reg_pad_info extends StructObject {
	public freenect_reg_pad_info() {
		super();
	}
	@Field(0) 
	public short start_lines() {
		return this.io.getShortField(this, 0);
	}
	@Field(0) 
	public freenect_reg_pad_info start_lines(short start_lines) {
		this.io.setShortField(this, 0, start_lines);
		return this;
	}
	@Field(1) 
	public short end_lines() {
		return this.io.getShortField(this, 1);
	}
	@Field(1) 
	public freenect_reg_pad_info end_lines(short end_lines) {
		this.io.setShortField(this, 1, end_lines);
		return this;
	}
	@Field(2) 
	public short cropping_lines() {
		return this.io.getShortField(this, 2);
	}
	@Field(2) 
	public freenect_reg_pad_info cropping_lines(short cropping_lines) {
		this.io.setShortField(this, 2, cropping_lines);
		return this;
	}
}

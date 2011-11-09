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
package org.openimaj.hardware.kinect.freenect;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.openimaj.hardware.kinect.freenect.libfreenectLibrary.freenect_resolution;

/**
 * <i>native declaration : /usr/include/stdint.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("freenect") 
@SuppressWarnings({"unchecked", "rawtypes"})
public class freenect_frame_mode extends StructObject {
	public freenect_frame_mode() {
		super();
	}
	public freenect_frame_mode(Pointer pointer) {
		super(pointer);
	}
	/// < unique ID used internally.  The meaning of values may change without notice.  Don't touch or depend on the contents of this field.  We mean it.
	@Field(0) 
	public int reserved() {
		return this.io.getIntField(this, 0);
	}
	/// < unique ID used internally.  The meaning of values may change without notice.  Don't touch or depend on the contents of this field.  We mean it.
	@Field(0) 
	public freenect_frame_mode reserved(int reserved) {
		this.io.setIntField(this, 0, reserved);
		return this;
	}
	/**
	 * < Resolution this freenect_frame_mode describes, should you want to find it again with freenect_find_*_frame_mode().<br>
	 * C type : freenect_resolution
	 */
	@Field(1) 
	public ValuedEnum<freenect_resolution > resolution() {
		return this.io.getEnumField(this, 1);
	}
	/**
	 * < Resolution this freenect_frame_mode describes, should you want to find it again with freenect_find_*_frame_mode().<br>
	 * C type : freenect_resolution
	 */
	@Field(1) 
	public freenect_frame_mode resolution(ValuedEnum<freenect_resolution > resolution) {
		this.io.setEnumField(this, 1, resolution);
		return this;
	}
	/// < Total buffer size in bytes to hold a single frame of data.  Should be equivalent to width * height * (data_bits_per_pixel+padding_bits_per_pixel) / 8
	@Field(2) 
	public int bytes() {
		return this.io.getIntField(this, 2);
	}
	/// < Total buffer size in bytes to hold a single frame of data.  Should be equivalent to width * height * (data_bits_per_pixel+padding_bits_per_pixel) / 8
	@Field(2) 
	public freenect_frame_mode bytes(int bytes) {
		this.io.setIntField(this, 2, bytes);
		return this;
	}
	/// < Width of the frame, in pixels
	@Field(3) 
	public short width() {
		return this.io.getShortField(this, 3);
	}
	/// < Width of the frame, in pixels
	@Field(3) 
	public freenect_frame_mode width(short width) {
		this.io.setShortField(this, 3, width);
		return this;
	}
	/// < Height of the frame, in pixels
	@Field(4) 
	public short height() {
		return this.io.getShortField(this, 4);
	}
	/// < Height of the frame, in pixels
	@Field(4) 
	public freenect_frame_mode height(short height) {
		this.io.setShortField(this, 4, height);
		return this;
	}
	/// < Number of bits of information needed for each pixel
	@Field(5) 
	public byte data_bits_per_pixel() {
		return this.io.getByteField(this, 5);
	}
	/// < Number of bits of information needed for each pixel
	@Field(5) 
	public freenect_frame_mode data_bits_per_pixel(byte data_bits_per_pixel) {
		this.io.setByteField(this, 5, data_bits_per_pixel);
		return this;
	}
	/// < Number of bits of padding for alignment used for each pixel
	@Field(6) 
	public byte padding_bits_per_pixel() {
		return this.io.getByteField(this, 6);
	}
	/// < Number of bits of padding for alignment used for each pixel
	@Field(6) 
	public freenect_frame_mode padding_bits_per_pixel(byte padding_bits_per_pixel) {
		this.io.setByteField(this, 6, padding_bits_per_pixel);
		return this;
	}
	/// < Approximate expected frame rate, in Hz
	@Field(7) 
	public byte framerate() {
		return this.io.getByteField(this, 7);
	}
	/// < Approximate expected frame rate, in Hz
	@Field(7) 
	public freenect_frame_mode framerate(byte framerate) {
		this.io.setByteField(this, 7, framerate);
		return this;
	}
	/// < If 0, this freenect_frame_mode is invalid and does not describe a supported mode.  Otherwise, the frame_mode is valid.
	@Field(8) 
	public byte is_valid() {
		return this.io.getByteField(this, 8);
	}
	/// < If 0, this freenect_frame_mode is invalid and does not describe a supported mode.  Otherwise, the frame_mode is valid.
	@Field(8) 
	public freenect_frame_mode is_valid(byte is_valid) {
		this.io.setByteField(this, 8, is_valid);
		return this;
	}
}

/*
 * Copyright 2005-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.laszlo.invoker.impl;

public class Hoge {

	private String str;

	private Integer i;

	private Long l;

	private Double d;

	private Float f;

	public Double getD() {
		return d;
	}

	public void setD(Double d) {
		this.d = d;
	}

	public Float getF() {
		return f;
	}

	public void setF(Float f) {
		this.f = f;
	}

	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public Long getL() {
		return l;
	}

	public void setL(Long l) {
		this.l = l;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String hello() {
		return "hoge hello";
	}

}

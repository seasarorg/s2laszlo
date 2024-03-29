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
package org.seasar.laszlo.javarpc;

/**
 * 
 * @author u1hoshino
 * 
 */
public class ReturnObject {

	private boolean success;

	private String exception;

	private String exceptionMessage;

	private Object object;

	public ReturnObject(final Object object) {
		this.object = object;
		this.success = true;
	}

	public ReturnObject(final String exception, final String exceptionMessage) {
		this.exception = exception;
		this.exceptionMessage = exceptionMessage;
		this.success = false;
	}

	public boolean isSuccess() {
		return success;
	}

	public Object getObject() {
		return object;
	}

	public String getException() {
		return exception;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

}

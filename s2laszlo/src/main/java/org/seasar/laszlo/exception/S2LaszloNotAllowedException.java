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
package org.seasar.laszlo.exception;

/**
 * 
 * @author u1hoshino
 * 
 */
public class S2LaszloNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public S2LaszloNotAllowedException() {
		super();
	}

	public S2LaszloNotAllowedException(String message) {
		super(message);
	}

	public S2LaszloNotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public S2LaszloNotAllowedException(Throwable cause) {
		super(cause);
	}
}

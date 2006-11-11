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
package org.seasar.laszlo.invoker;

import java.lang.annotation.Annotation;

public class ComponentInvokerResult {

	private boolean returnTypeVoid;

	private Object result;

	private Exception exception;

	private Annotation[] classAnnotations;

	private Annotation[] methodAnnotations;

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public boolean isReturnTypeVoid() {
		return returnTypeVoid;
	}

	public void setReturnTypeVoid(boolean returnTypeVoid) {
		this.returnTypeVoid = returnTypeVoid;
	}

	public Annotation[] getClassAnnotations() {
		return classAnnotations;
	}

	public void setClassAnnotations(Annotation[] classAnnotations) {
		this.classAnnotations = classAnnotations;
	}

	public Annotation[] getMethodAnnotations() {
		return methodAnnotations;
	}

	public void setMethodAnnotations(Annotation[] methodAnnotations) {
		this.methodAnnotations = methodAnnotations;
	}

}

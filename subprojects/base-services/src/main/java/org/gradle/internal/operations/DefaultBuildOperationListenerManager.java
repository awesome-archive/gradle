/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.internal.operations;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultBuildOperationListenerManager implements BuildOperationListenerManager {

    private final Lock lock = new ReentrantLock();
    private final Set<BuildOperationListener> listeners = new LinkedHashSet<BuildOperationListener>();

    private final BuildOperationListener broadcaster = new BuildOperationListener() {
        @Override
        public void started(BuildOperationDescriptor buildOperation, OperationStartEvent startEvent) {
            lock.lock();
            try {
                for (BuildOperationListener listener : listeners) {
                    listener.started(buildOperation, startEvent);
                }
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void progress(OperationIdentifier operationIdentifier, OperationProgressEvent progressEvent) {
            lock.lock();
            try {
                for (BuildOperationListener listener : listeners) {
                    listener.progress(operationIdentifier, progressEvent);
                }
            } finally {
                lock.unlock();
            }

        }

        @Override
        public void finished(BuildOperationDescriptor buildOperation, OperationFinishEvent finishEvent) {
            lock.lock();
            try {
                for (BuildOperationListener listener : listeners) {
                    listener.finished(buildOperation, finishEvent);
                }
            } finally {
                lock.unlock();
            }
        }
    };

    @Override
    public void addListener(BuildOperationListener listener) {
        lock.lock();
        try {
            listeners.add(listener);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void removeListener(BuildOperationListener listener) {
        lock.lock();
        try {
            listeners.remove(listener);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public BuildOperationListener getBroadcaster() {
        return broadcaster;
    }
}

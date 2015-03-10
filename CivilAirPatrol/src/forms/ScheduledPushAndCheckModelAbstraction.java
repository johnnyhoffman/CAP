/**
 *  Copyright 2015 Dana Vold, Johnny Hoffman, Robert Wassenaar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package forms;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import common.GlobalConstants;

public abstract class ScheduledPushAndCheckModelAbstraction extends
        ScheduledPushModelAbstraction {

    public interface OnIntervalListener {
        public void onInterval();
    }

    private OnIntervalListener onIntervalListener;

    private final ScheduledExecutorService scheduler;
    public final ScheduledFuture<?> checkerHandle;

    protected ScheduledPushAndCheckModelAbstraction() {
        super();
        scheduler = Executors.newScheduledThreadPool(1);

        final Runnable checker = new Runnable() {
            public void run() {
                if (onIntervalListener != null) {
                    onIntervalListener.onInterval();
                }
            }
        };
        checkerHandle = scheduler.scheduleAtFixedRate(checker,
                GlobalConstants.CHECK_DELAY, GlobalConstants.CHECK_DELAY,
                GlobalConstants.CHECK_DELAY_UNITS);
    }

    public void setOnIntervalListener(OnIntervalListener l) {
        this.onIntervalListener = l;
    }

}

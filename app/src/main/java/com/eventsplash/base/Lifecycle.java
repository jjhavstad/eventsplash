package com.eventsplash.base;

import androidx.annotation.NonNull;

/**
 * Created by jonathanhavstad on 2/24/18.
 */

public interface Lifecycle {
    interface View {

    }

    interface ViewModel {
        void onViewResumed();
        void onViewAttached(@NonNull Lifecycle.View viewCallback);
        void onViewDetached();
    }
}

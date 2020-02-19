/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.stunner.core.client.canvas.controls.keyboard;

import org.kie.workbench.common.stunner.core.client.canvas.Canvas;
import org.kie.workbench.common.stunner.core.client.canvas.controls.CanvasControl;
import org.kie.workbench.common.stunner.core.client.event.keyboard.KeyboardEvent;
import org.kie.workbench.common.stunner.core.client.session.ClientSession;

public interface KeyboardControl<C extends Canvas, S extends ClientSession> extends CanvasControl<C>,
                                                                                    CanvasControl.SessionAware<S> {

    KeyboardControl<C, S> addKeyShortcutCallback(final KeyShortcutCallback shortcutCallback);

    interface KeyShortcutCallback {

        void onKeyShortcut(final KeyboardEvent.Key... keys);

        default void onKeyUp(final KeyboardEvent.Key key) {
        }
    }

    //
    //
    //Kogito

    class KogitoOpts {

        public static final KogitoOpts DEFAULT = new KogitoOpts(false);

        private final boolean repeat;

        public KogitoOpts(final boolean repeat) {
            this.repeat = repeat;
        }

        public boolean getRepeat() {
            return repeat;
        }
    }

    interface KogitoKeyShortcutCallback extends KeyShortcutCallback {

        default KogitoOpts getOpts() {
            return KogitoOpts.DEFAULT;
        }

        String getKeyCombination();

        String getLabel();
    }

    interface KogitoKeyPress extends KogitoKeyShortcutCallback {

        @Override
        default void onKeyShortcut(final KeyboardEvent.Key... keys) {
            onKeyDown();
        }

        @Override
        default void onKeyUp(final KeyboardEvent.Key key) {
            throw new RuntimeException("Keyup shouldn't be called on KeyPress events");
        }

        void onKeyDown();
    }

    interface KogitoKeyShortcutDownThenUp extends KogitoKeyShortcutCallback {

        @Override
        default void onKeyShortcut(final KeyboardEvent.Key... keys) {
            onKeyDown();
        }

        @Override
        default void onKeyUp(final KeyboardEvent.Key key) {
            onKeyUp();
        }

        void onKeyUp();

        void onKeyDown();
    }
}

/**
 * Copyright 2014 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.bourseje.client.application.identification;

import javax.inject.Inject;

import com.arcbees.bourseje.client.resources.PageIdentificationResources;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.FormElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.google.gwt.query.client.GQuery.$;

public class IdentificationView extends ViewWithUiHandlers<IdentificationUiHandlers> implements IdentificationPresenter.MyView {
    interface Binder extends UiBinder<Widget, IdentificationView> {
    }

    @UiField
    FormElement form;
    @UiField
    InputElement code;
    @UiField
    DivElement invalidCodeMessage;
    @UiField
    PageIdentificationResources pageResources;

    @Inject
    IdentificationView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        initSubmit();
    }

    @Override
    public void hideInvalidCodeError() {
        $(invalidCodeMessage).hide();
        $(code).removeClass(pageResources.style().input_error());

        code.setValue("");
    }

    @Override
    public void showInvalidCodeError() {
        $(invalidCodeMessage).show();
        $(code).addClass(pageResources.style().input_error());

        code.setValue("");
    }

    private void initSubmit() {
        $(form).on("submit", new Function() {
            @Override
            public void f() {
                getUiHandlers().onSubmit(code.getValue().toUpperCase());
            }
        });
    }
}

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

package com.arcbees.bourseje.client.application.confirmvote;

import javax.inject.Inject;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ConfirmVoteView extends ViewWithUiHandlers<ConfirmVoteUiHandlers> implements ConfirmVotePresenter.MyView {
    interface Binder extends UiBinder<Widget, ConfirmVoteView> {
    }

    @UiField
    DivElement name;
    @UiField
    DivElement company;
    @UiField
    Image picture;

    @Inject
    ConfirmVoteView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setName(String name) {
        this.name.setInnerText(name);
    }

    @Override
    public void setCompany(String company) {
        this.company.setInnerText(company);
    }

    @Override
    public void setPicture(ImageResource picture) {
        this.picture.setResource(picture);
    }

    @UiHandler("confirm")
    void onConfirm(ClickEvent event) {
        getUiHandlers().onConfirmClicked();
    }
}

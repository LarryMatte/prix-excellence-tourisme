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

package com.arcbees.bourseje.server.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.arcbees.bourseje.server.dao.VoteItemDao;
import com.arcbees.bourseje.server.exception.InactiveVoteException;
import com.arcbees.bourseje.server.exception.NoVoteException;
import com.arcbees.bourseje.server.guice.ServerModule;
import com.arcbees.bourseje.shared.VoteItem;

public class VoteService {
    private final Calendar pollDate;
    private final VoteItemDao voteItemDao;

    @Inject
    VoteService(
            @Named(ServerModule.POLL_DATE) Calendar pollDate,
            VoteItemDao voteItemDao) {
        this.pollDate = pollDate;
        this.voteItemDao = voteItemDao;
    }

    public List<VoteItem> getPollItems() {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        if (today.after(pollDate)) {
            return voteItemDao.getAll();
        } else if (sameDay(today)) {
            throw new InactiveVoteException();
        } else {
            throw new NoVoteException();
        }
    }

    private boolean sameDay(Calendar today) {
        return today.get(Calendar.YEAR) == pollDate.get(Calendar.YEAR) &&
                today.get(Calendar.DAY_OF_YEAR) == pollDate.get(Calendar.DAY_OF_YEAR);
    }
}

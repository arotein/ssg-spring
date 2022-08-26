package com.youngjo.ssg.domain.purchase.repository;

import com.youngjo.ssg.domain.purchase.domain.UserPurchase;

public interface UserPurchaseRepository {
    void saveUserPurchase(UserPurchase userPurchase);
}

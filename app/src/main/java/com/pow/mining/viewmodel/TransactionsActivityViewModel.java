package com.pow.mining.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pow.mining.model.Transaction;
import com.pow.mining.repo.TransactionsActivityRepo;

import java.util.List;

public class TransactionsActivityViewModel extends ViewModel {

    private final TransactionsActivityRepo repo;
    private final LiveData<String> errorMessage;
    private final LiveData<List<Transaction>> liveData;


    public TransactionsActivityViewModel() {
        repo = new TransactionsActivityRepo();
        errorMessage = repo.getError();
        liveData = repo.getAllTransactions();
    }

    public void getTransactions(String userId) {
        repo.getTransactions(userId);
    }

    public LiveData<List<Transaction>> getAllTransactions() {

        return liveData;
    }


    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }


}

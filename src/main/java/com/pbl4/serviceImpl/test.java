package com.pbl4.serviceImpl;


import com.pbl4.model.bean.HistoryModel;
import com.pbl4.serviceImpl.HistoryService;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        HistoryService historyService = HistoryService.getInstance();

        // Kiểm thử phương thức findByUserId
        long testUserId = 1;
        HistoryModel historyByUserId = historyService.findByUserId(testUserId);
        System.out.println("History by user ID: " + historyByUserId.calculateEloChange(testUserId));

        // Kiểm thử phương thức findAllByPlayerId
        long testPlayerId = 2;
        ArrayList<HistoryModel> historyList = historyService.findAllByPlayerId(testPlayerId);
        System.out.println("History list by player ID:");
        for (HistoryModel history : historyList) {
            System.out.println(history);
        }

        // Kiểm thử phương thức insert
        HistoryModel newHistory = new HistoryModel();
        newHistory.setPlayerId(3);
        newHistory.setOpponentId(4);
        newHistory.setResult("win");
        historyService.insert(newHistory);
        System.out.println("Inserted new history record: " + newHistory);
    }
}

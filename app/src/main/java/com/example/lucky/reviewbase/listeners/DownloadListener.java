package com.example.lucky.reviewbase.listeners;

public interface DownloadListener {

    void onProgress(int progress); // 当前下载进度

    void onSuccess(); // 下载成功

    void onFailed(); // 下载失败

    void onCanceled(); // 下载完成

    void onPaused(); // 暂停
}

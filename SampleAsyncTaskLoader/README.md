# これは何？

Android、AsyncTaskLoader を用いて非同期処理を行う  
実行する処理は HttpUrlConnection  
それだけ

　  
　  

# 課題
* 通信完了時に MyAsyncTaskLoader.setIsRunning(false) しないといけないのが非常にばからしい
  * ［要検討］これ不要なのでは？
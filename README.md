# BGCountdownTimer
バックグラウンドでも動作するAndroid用カウントダウンタイマーです。
本リポジトリではAndroidアプリ「推し勉」のソースコードの一部を再構成して公開しています。

アプリURL: https://play.google.com/store/apps/details?id=dev.jnaka9.motivetimer

## アプリ概要(予定)
勉強用アプリの中には、ユーザーが勉強に費やす時間を計測・記録するものがあります。
ユーザーが勉強を終えてアプリに復帰した際に勉強時間を記録するには、必ず勉強を開始した時間を保持しておく必要があります。
しかし、ユーザーがアプリを閉じている間にAndroidシステムによってアプリがキルされると、開始時間のデータが消えてカウントダウンセッションが初期化される場合があります。
カウントダウンセッションが初期化されるとユーザーは勉強の記録を失うことになり、アプリの信用低下に繋がります。
そこで、開始時間が消えない仕組みが必要です。

本アプリでは、開始時間を含むカウントダウンセッションの情報をアプリ内のデータベースに記録しておくことで、Androidシステム、あるいはユーザーによってアプリがキルされてもカウントダウンが初期化されないようにしました。

## アプリの主な機能(予定)

| 機能 | 説明 |
| :--- | :--- |
| カウントダウンの永続化 | アプリがキルされてもカウントダウンが初期化されません |
| カウントダウンの表示 | アプリがアクティブのとき、画面上にカウントダウンを表示します |
| スタート・ストップ | カウントダウンを画面操作で停止、再開させることができます　|
| リセット | 画面操作でカウントダウンを初期化できます |
| 通知 | カウントダウンが0になると通知します |

## 使用ライブラリ（抜粋）

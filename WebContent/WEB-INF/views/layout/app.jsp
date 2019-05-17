<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>タスクリスト</title>
    </head>
    <body>
        <div id = "wrapper">
            <div id = "header">
                <h1>タスクリスト アプリケーション</h1>
            </div>
            <div id = "content">
                <!-- paramタグでcontentと名前をつけた部分を、
                 このひな型のこの部分に挿入することができる。-->
                ${param.content}
            </div>
            <div id = "footer">
                by Kento Suzuki.
            </div>
        </div>
    </body>
</html>
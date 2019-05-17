package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Task;

public class TasksValidator {
    //バリデーションを実行する
    public static List<String> validate(Task t){
        List<String> errors = new ArrayList<String>();

        String content_error = _validateContent(t.getContent());
        if(!content_error.equals("")){
            errors.add(content_error);
        }

        return errors;
    }

    private static String _validateContent(String content){
        if(content == null || content.equals("")){
            return "タスク内容を入力してください。";
        }
        return "";
    }

}

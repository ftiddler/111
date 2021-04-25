package com.example.demo.common;

/**
 * @author Huang Yuchen
 * @version 1.0
 * @date 2021-03-29 20:56
 */
public class Constants {

    public final static char SIGN_ATTACHMENT = '2';
    public final static char SIGN_USER_FILES = '1';

    public final static char NOT_VIEWED = '0';
    public final static char NOT_SUBMITTED = '1';
    public final static char COMPLETED = '2';

    public final static String SELECT_TASK_FAIL_MSG = "查询任务列表出错";
    public final static String CREATE_TASK_FAILED_MSG = "创建新任务失败";
    public final static String CREATE_USER_TASK_FAILED_MSG = "创建用户任务信息失败";
    public final static String UPLOAD_FILE_FAILED = "上传文件失败";
    public final static String UPLOAD_FILE_IS_EMPTY = "上传文件为空，访问被拒绝";
    public final static String INSERT_INTO_T_FILE = "向文件表插入数据失败";
    public final static String NOT_FIND_TARGET_FILES = "目标文件不存在";
    public final static String ZIP_FILE_CREATE_FAIL = "打包文件生成失败";
    public final static String GET_DOWNLOAD_FILE_SRC_FAIL = "获取下载链接失败";
    public final static String FILE_IS_NULL_OR_REQUEST_PARAMS_ERROR = "文件为空或请求参数错误";
    public final static String DELETE_FILE_FAILED = "删除目标文件失败";
    public final static String DELETE_FROM_T_FILE = "删除文件表数据失败";

    /**
     * 任务类型
     */
    public enum TaskType {
        MY_RELEASED(1, "我的发布"), MY_TASK(2, "我的任务");

        private final int value;
        private final String describe;

        TaskType(int value, String describe) {
            this.value = value;
            this.describe = describe;
        }

        public int getValue() {return this.value;}

        public String getDescribe() {return this.describe;}
    }

    /**
     * 返回状态码
     */
    public enum ResultCode {
        SUCCESS(20000, "操作成功"), FAILED(20001, "操作失败"),
        UPLOAD_SUCCESS(20002, "文件上传成功"), UPLOAD_FAILED(20003, "文件上传失败");

        private final int code;
        private final String describe;

        ResultCode(int code, String describe) {
            this.code = code;
            this.describe = describe;
        }

        public int getCode() {return this.code;}

        public String getDescribe() {return this.describe;}
    }

    public enum MatchType {
        TITLE(1, "title"), SUMMARY(2, "summary");

        private final int value;
        private final String describe;

        MatchType (int value, String describe) {
            this.value = value;
            this.describe = describe;
        }

        public int getValue() {return this.value;}

        public String getDescribe() {return this.describe;}
    }

}

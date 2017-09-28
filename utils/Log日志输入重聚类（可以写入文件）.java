public class LogUtil {

    public static final boolean DEBUG = true;   //true 打开；false 关闭

    //日志写入文件开关
    private static final boolean MYLOG_WRITE_TO_FILE = true;

    // 日志文件在sdcard中的路径
    private static String MYLOG_PATH_SDCARD_DIR = Environment.getExternalStorageDirectory().getPath();

    // sd卡中日志文件的最多保存天数
    private static final int SDCARD_LOG_FILE_SAVE_DAYS = 7;

    // 本类输出的日志文件名称
    private static String MYLOGFILEName = "AppName.txt";

    // 日志的输出格式
    private static final SimpleDateFormat myLogSdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    // 日志文件格式
    private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }

        if (MYLOG_WRITE_TO_FILE) {
            writeLogtoFile("e", tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }

        if (MYLOG_WRITE_TO_FILE) {
            writeLogtoFile("d", tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }

        if (MYLOG_WRITE_TO_FILE) {
            writeLogtoFile("i", tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }

        if (MYLOG_WRITE_TO_FILE) {
            writeLogtoFile("w", tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }

        if (MYLOG_WRITE_TO_FILE) {
            writeLogtoFile("v", tag, msg);
        }
    }


    /**
     * 没有必要请勿用
     *
     * @param tag
     * @param msg
     */
    public static void destroy(String tag, String msg) {
        if (MYLOG_WRITE_TO_FILE) {
            Log.wtf(tag, msg);
        }
    }

    /**
     * 打开日志文件并写入日志
     **/
    private static void writeLogtoFile(String mylogtype, String tag, String text) {// 新建或打开日志文件
        Date nowtime = new Date();
        String needWriteFiel = logfile.format(nowtime);
        String needWriteMessage = myLogSdf.format(nowtime) + "    " + mylogtype
                + "    " + tag + "    " + text;
        File file = new File(MYLOG_PATH_SDCARD_DIR, needWriteFiel
                + MYLOGFILEName);
        try {

            delFile();

            FileWriter filerWriter = new FileWriter(file, true);//后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(needWriteMessage);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 删除制定的日志文件
     * */
    public static void delFile() {// 删除日志文件
        String needDelFiel = logfile.format(getDateBefore());
        File file = new File(MYLOG_PATH_SDCARD_DIR, needDelFiel + MYLOGFILEName);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
     * */
    private static Date getDateBefore() {
        Date nowtime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowtime);
        now.set(Calendar.DATE, now.get(Calendar.DATE)
                - SDCARD_LOG_FILE_SAVE_DAYS);
        return now.getTime();
    }
}

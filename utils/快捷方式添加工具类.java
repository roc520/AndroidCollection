/**
 * 快捷方式添加工具类
 */
public class ShortCutUtils {
    /**
     * 添加当活动为启动项
     *
     * @param cx
     * @param name 快捷方式名称
     */
    public static void addShortcut(Activity cx, String name) {
        // 创建快捷方式的intent广播
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // 添加快捷名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        // 快捷图标是允许重复
        shortcut.putExtra("duplicate", false);
        // 快捷图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(cx, android.R.drawable.presence_online);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        // 我们下次启动要用的Intent信息
        Intent carryIntent = new Intent(Intent.ACTION_MAIN);
        carryIntent.putExtra("name", name);
        carryIntent.setClassName(cx.getPackageName(), cx.getClass().getName());
        carryIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // 添加携带的Intent
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, carryIntent);
        // 发送广播
        cx.sendBroadcast(shortcut);
    }
}
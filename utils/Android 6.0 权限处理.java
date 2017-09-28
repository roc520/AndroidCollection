     if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(StartActivity.this, Manifest.permission.CAMERA);
                    int permissionWrite = ActivityCompat.checkSelfPermission(StartActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED || permissionWrite != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(StartActivity.this,
                                new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 777);
                    } else {
                        //已经获取该权限时的处理
                        JumpOther();
                    }
                } else {
                        //Android版本低于6.0时的处理
                    JumpOther();
                }

    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            //就像onActivityResult一样这个地方就是判断你是从哪来的。
            case 777:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //权限获取成功
                    JumpOther();
                } else {
                    CustomToast.showToast(this, "请务必打开权限。");
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
// MainActivity.java
package com.example.appverify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AppListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        checkApps();
    }

    private void checkApps() {
        List<AppInfo> appList = new ArrayList<>();
        PackageManager pm = getPackageManager();
        
        for (PackageInfo pkg : pm.getInstalledPackages(PackageManager.GET_SIGNATURES)) {
            try {
                String signatureHash = getSignatureHash(pkg.signatures[0]);
                boolean isOfficial = checkSignatureDatabase(pkg.packageName, signatureHash);
                
                appList.add(new AppInfo(
                    pkg.applicationInfo.loadLabel(pm).toString(),
                    pkg.packageName,
                    isOfficial,
                    signatureHash
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        adapter = new AppListAdapter(appList);
        recyclerView.setAdapter(adapter);
    }

    private String getSignatureHash(Signature signature) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(signature.toByteArray());
        StringBuilder hexString = new StringBuilder();
        
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        
        return hexString.toString();
    }

    private boolean checkSignatureDatabase(String packageName, String signatureHash) {
        // Здесь должна быть реализация проверки против базы известных подписей
        // Это может быть локальная БД или запрос к серверу
        return true; // Заглушка
    }
}
package BackWork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import panels.OnlinePanel;

import java.io.*;

public class OnlineData {

    private long sleepTime = 2010;
    private DataConfig oldData = new DataConfig();
    public OnlineData(OnlinePanel oP) {
        try {
            Runtime rt = Runtime.getRuntime();
            printOutput errorReported, outputMessage;

            Process proc = rt.exec("/home/vahid/anaconda3/bin/python " + System.getProperty("user.dir") + "/run.py");
            errorReported = new printOutput(proc.getErrorStream(), "ERROR");
            outputMessage = new printOutput(proc.getInputStream(), "OUTPUT");
            errorReported.start();
            outputMessage.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final OnlinePanel onlinePanel = oP;
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Gson gson = new GsonBuilder().create();
                while (true) {
                    try {
                        System.out.println("go");
                        BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/DataFile"));
                        StringBuilder jsonData = new StringBuilder();
                        String str;
                        while ((str = reader.readLine()) != null)
                            jsonData.append(str);
                        DataConfig newData = gson.fromJson(jsonData.toString(), DataConfig.class);
                        if (!oldData.equals(newData)) {
//                            System.out.println(newData);
                            onlinePanel.setData(newData);
                            oldData = newData;
                        }
                        reader.close();
                        sleepTime = (long) newData.getUpdateInterval() + 10;
                        Thread.sleep(sleepTime);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    private class printOutput extends Thread {
        InputStream is = null;

        printOutput(InputStream is, String type) {
            this.is = is;
        }

        public void run() {
            String s = null;
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is));
                while ((s = br.readLine()) != null) {
//                    System.out.println(s);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

}

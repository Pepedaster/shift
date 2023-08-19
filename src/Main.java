import java.io.*;
import java.util.ArrayList;
import sort.MergeSort;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean incr = true;
        boolean typeS = false;
        boolean err1 = false;
        boolean bigM = false;
        boolean outputFileB = false;
        String outputFileN = "";
        ArrayList<String> fileNames = new ArrayList<>(10);
        int fileCount = 0;
        ArrayList<ArrayList> arrs = new ArrayList<>();
        long bb = 8589934592L;


        //region Аргументы
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s":
                    typeS = true;
                    err1 = true;
                    break;

                case "-i":
                    typeS = false;
                    err1 = true;
                    break;

                case "-d":
                    incr = false;

                case "-b":
                    bigM = true;
            }
            if ((args[i].indexOf(".txt") != -1)){
                if (outputFileB == false){
                    outputFileN = args[i];
                    outputFileB = true;
                    continue;
                }
                fileNames.add(args[i]);
                fileCount++;

            }
        }
        if (err1 == false) {
            System.out.println("err");
        }
        //endregion



        MergeSort ms = new MergeSort(arrs);
        if ((bigM)&&(fileCount == 2)){
            ms.BigSort(fileNames.get(0),fileNames.get(1));
        }
        for (int i = 0; i<fileCount;i++){
            if (typeS == true) {
                arrs.add(ms.OpenSFile(fileNames.get(i)));
            }else {
                arrs.add(ms.OpenFile(fileNames.get(i)));
            }
        }

        ArrayList<Long> mss;
        if (arrs.size() == 2){
            mss = ms.EasySort(arrs.get(0),arrs.get(1));
        } else if (arrs.size() % 2==1) {
            mss = ms.OddSort();
        } else {
            mss = ms.NormSort();
        }


        //region Вывод
        File file = new File(outputFileN);
        try (FileWriter writer = new FileWriter(file)) {
            if (incr == true){
                for (int i = 0;i< mss.size();i++){
                writer.write(mss.get(i).toString());
                writer.write("\n");
                System.out.println(mss.get(i));
                }


            } else {
                for (int i = mss.size()-1; i>0;i--){
                    writer.write(mss.get(i).toString());
                    writer.write("\n");
                }
            }
        }
        catch (IOException ex)
        {}
        //endregion
    }

}


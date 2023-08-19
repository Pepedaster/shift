package sort;
import java.io.*;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Scanner;

public class MergeSort {

    long b = 8589934592L;
    ArrayList<ArrayList> arrs = new ArrayList<>();
    public MergeSort(ArrayList<ArrayList> arrayList) throws Exception {
        arrs = arrayList;
    }

    //region Sort
    public ArrayList<Long> EasySort(ArrayList<Long> arrA, ArrayList<Long> arrB){
        int arrAmount = arrA.size() + arrB.size();
        ArrayList<Long> arrC = new ArrayList<>(arrAmount);
        int countA = 0;
        int countB = 0;
        while (arrAmount != countA+countB){
            if ((arrA.size()>countA)&(arrB.size()>countB)) {
                if (arrA.get(countA) <= arrB.get(countB)) {
                    arrC.add(arrA.get(countA));
                    countA++;
                } else {
                    arrC.add(arrB.get(countB));
                    countB++;
                }
            } else {
                if (arrA.size()==countA){
                    arrC.add(arrB.get(countB));
                    countB++;
                } else {
                    arrC.add(arrA.get(countA));
                    countB++;
                }
            }
        }
        return arrC;

    }

    public ArrayList<Long> OddSort(){
        int arrCCount = arrs.size();
        ArrayList<ArrayList> newCArrs = new ArrayList<>();
        ArrayList<Long> arrCZero = arrs.get(0);
        ArrayList<ArrayList> nArrs = arrs;

        while (nArrs.size() > 1){
            int count = 1;
            while (count + 1 < nArrs.size()){
                ArrayList a = nArrs.get(count);
                ArrayList<Long> b = nArrs.get(count+1);
                ArrayList<Long> c = EasySort(a,b);
                newCArrs.add(c);
                count++;
            }
            nArrs = new ArrayList<>(newCArrs);
            newCArrs.clear();
        }
        return EasySort(arrCZero, nArrs.get(0));
    }

    public ArrayList<Long> NormSort(){
        int arrCCount = arrs.size();
        ArrayList<ArrayList> newCArrs = new ArrayList<>();
        ArrayList<ArrayList> nArrs = arrs;

        while (nArrs.size() > 2){
            int count = 0;
            while (count + 1 < nArrs.size()){
                ArrayList<Long> a = nArrs.get(count);
                ArrayList<Long> b = nArrs.get(count+1);
                ArrayList<Long> c = EasySort(a,b);
                newCArrs.add(c);
                count++;
            }
            nArrs = new ArrayList<>(newCArrs);
            newCArrs.clear();
        }
        return EasySort(nArrs.get(0), nArrs.get(1));
    }

    public ArrayList<Long> BigSort(String fna, String fnb){
        int count = 0;
        ArrayList<Long> arrC = new ArrayList<>();
        try (Scanner scannerA = new Scanner(fna)){
            Scanner scannerB = new Scanner(fnb);
            Long a = null;
            Long b = null;

            while ((scannerA.hasNext())&&(scannerB.hasNext())){

                if (!scannerA.hasNext()) { arrC.add(scannerB.nextLong()); }
                if (!scannerB.hasNext()) { arrC.add(scannerA.nextLong()); }
                if ((a = scannerA.nextLong()) < (b = scannerB.nextLong())){
                    arrC.add(a);
                } else {
                    arrC.add(b);
                }
            }
            if (!scannerA.hasNext()) {
                while (scannerB.hasNext()){
                    arrC.add(b);
                }
            }
            if (!scannerB.hasNext()){
                while (scannerA.hasNext()){
                    arrC.add(a);
                }
            }

        }
        return arrC;
    }

    //endregion

    //region Open Files
    public ArrayList<Long> OpenFile(String fn){
        File file = new File(fn);
        try(Scanner scanner = new Scanner(file))
        {
            ArrayList<Long> arr = new ArrayList<>();
            Long n;
            while (scanner.hasNext()){
                arr.add(scanner.nextLong());

                //System.out.println((char)n);
            }

            return arr;
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public ArrayList<Long> OpenSFile(String fn){
        File file = new File(fn);
        ArrayList<Long> arr = new ArrayList<>();
        try(FileReader reader = new FileReader(file)) {
            BufferedReader readr = new BufferedReader(reader);
            String line = readr.readLine();
            while (line != null){
                if (!line.contains(" ")){

                    line = line.replaceAll("[^0-9]+", "");
                    arr.add(Long.valueOf(line));
                    line = readr.readLine();
                } else {
                    line = readr.readLine();
                }

            }

        } catch(IOException exception) {

        }
        return arr;
    }



    //endregion

}

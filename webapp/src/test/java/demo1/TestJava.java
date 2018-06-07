package demo1;

import com.github.nnkwrik.kiwiTheater.model.Vo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestJava {

    @Test
    public void testCollecitons() {

        ArrayList<Vo> livingList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Vo vo = new Vo();
            vo.setViewer(i);
            livingList.add(vo);
        }


        //TODO sort by viewer
        Collections.sort(livingList, new Comparator<Vo>() {
            @Override
            public int compare(Vo o1, Vo o2) {
                if (o1.getViewer() >= o2.getViewer()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        for (Vo vo : livingList) {
            System.out.println(vo.getViewer());
        }
    }

    @Test
    public void testSplit() {

        String key = "     sd sd    aa a  a    f";
        for (String i : key.split(" ")) {
            if (!i.trim().equals("")){

                System.out.println(i);
            }
        }

    }
}

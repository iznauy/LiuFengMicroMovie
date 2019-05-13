package cn.nju.edu.movie.common;

/**
 * Created on 13/05/2019.
 * Description:
 *
 * @author iznauy
 */
public enum Source {

    MAO_YAN("猫眼"),
    SHI_GUANG_WANG("时光网");

    private String name;

    Source(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}

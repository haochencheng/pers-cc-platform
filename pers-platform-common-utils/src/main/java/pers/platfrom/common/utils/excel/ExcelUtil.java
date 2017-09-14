package pers.platfrom.common.utils.excel;

import com.sun.rowset.internal.Row;
import javafx.scene.control.Cell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by cc on  2017/9/14
 */
public class ExcelUtil {

    /**
     *
     * 遍历xls表数据
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        InputStream inp = new FileInputStream("c:\\cc.xls");
        POIFSFileSystem fs = new POIFSFileSystem(inp);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        for (Sheet sheet : wb) {
            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.println(cell);
                }
            }
        }
        fs.close();
    }

}

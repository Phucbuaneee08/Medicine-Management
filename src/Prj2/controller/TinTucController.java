package Prj2.controller;

import Prj2.Service.CrawlInfo;
import Prj2.model.TinTuc;
import Prj2.model.TinTucBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;

public class TinTucController {
    @FXML private ListView<TinTuc> lvTinTuc ;
    @FXML private Button btnPre;
    private int page = 1;
    public TinTucController(Controller controller) throws IOException {
        FXMLLoader pane = new FXMLLoader(getClass().getResource("/Prj2/View/TienIch.fxml"));
        pane.setController(this);
        controller.setTienIchView(pane.load());
        this.btnPre.setOnAction(event -> {
            try {
                addTinTuc();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        this.getDataView();
    }
    CrawlInfo data = new CrawlInfo();
    public void getDataView() throws NullPointerException {
        lvTinTuc.getItems().addAll(data.crawlTinTuc(page));
        lvTinTuc.setCellFactory(listView -> new TinTucBox());
    }
    public void addTinTuc() throws IOException{
        this.page++;
        lvTinTuc.getItems().addAll(data.crawlTinTuc(this.page));
    }
}
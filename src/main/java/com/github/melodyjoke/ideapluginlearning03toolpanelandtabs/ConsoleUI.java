package com.github.melodyjoke.ideapluginlearning03toolpanelandtabs;

import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.api.IStock;
import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.api.StockImpl;
import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.api.bean.Data;
import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.api.bean.GoPicture;
import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.data.DataSetting;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "CallToPrintStackTrace"})
public class ConsoleUI {

    private JPanel mainPanel;

    private JTabbedPane tabbedPanel;

    private JPanel tabFirst;

    private JPanel tabSecond;

    private JScrollPane scrollPane;

    private JTable table;

    private JLabel picMin;

    private JLabel picDay;

    private final DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[][]{}, new String[]{"股票", "代码", "最新", "涨跌", "涨幅"});

    private final IStock stock = new StockImpl();

    public ConsoleUI() {
        // 初始数据
        table.setModel(defaultTableModel);
        addRows(DataSetting.instance.getGids());

        // 添加事件
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent event) {
                int row = table.getSelectedRow();
                Object value = table.getValueAt(row, 1);

                GoPicture goPicture = stock.queryGidGoPicture(value.toString());

                if (goPicture != null) {
                    try {
                        // 分钟K线
                        picMin.setSize(545, 300);
                        picMin.setIcon(new ImageIcon(new URL(goPicture.getMinurl())));

                        // 当日K线
                        picDay.setSize(545, 300);
                        picDay.setIcon(new ImageIcon(new URL(goPicture.getDayurl())));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public JTabbedPane getPanel() {
        return tabbedPanel;
    }

    public void addRows(List<String> gids) {
        // 查询
        List<Data> dataList = stock.queryPresetStockData(gids);

        // 清空
        int rowCount = defaultTableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            defaultTableModel.removeRow(0);
        }

        // 添加
        for (Data data : dataList) {
            defaultTableModel.addRow(new String[]{data.getName(), data.getGid(), data.getNowPri(), data.getIncrease(), data.getIncrePer()});

            table.setModel(defaultTableModel);
        }
    }
}

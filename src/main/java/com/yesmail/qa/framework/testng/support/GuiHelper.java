/**File name : GuiHelper
 * Description: Class for creating GUI and frame
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */


package com.yesmail.qa.framework.testng.support;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.yesmail.qa.framework.configuration.CommandLineArgs;
/***
 * This class provides functionality to create frame
 * updated rows with data
 * @author kapilag
 *
 */
class GuiHelper {

	private static DefaultTableModel tableModel_1 = new DefaultTableModel();
	private static boolean updateFlag = CommandLineArgs.getdisplayGuiFlag();

	// CReate Frame with Table
	protected static void createFrame() {
		JFrame guiFrame = new JFrame();
		guiFrame.setVisible(true);
		guiFrame.setSize(500, 550);
		JTable table = new JTable(tableModel_1);
		guiFrame.add(new JScrollPane(table));

	}

	/***
	 * Add the rows to table with data
	 * 
	 * @param a1
	 *            :Sno
	 * @param a2
	 *            :Test Method Name
	 * @param a3
	 *            :Status
	 */
	protected static void addRows(Integer a1, String a2, String a3) {
		tableModel_1.addRow(new Object[] { a1, a2, a3 });

	}

	protected static void addColoumn(String colName) {
		tableModel_1.addColumn(colName);
	}

	/***
	 * Update Method Name with status
	 * 
	 * @param methodName
	 * @param status
	 */
	protected static void checkAndUpdateStatus(String methodName, String status) {
		if (updateFlag) {
			for (int i = 0; i < tableModel_1.getRowCount(); i++) {
				if (tableModel_1.getValueAt(i, 1).toString()
						.contains(methodName)) {
					tableModel_1.setValueAt(status, i, 2);
					break;
				}
			}
		}
	}
}

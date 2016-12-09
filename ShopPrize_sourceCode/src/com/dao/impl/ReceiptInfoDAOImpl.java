package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.bean.Image;
import com.bean.Item;
import com.bean.ReceiptInfo;
import com.dao.ReceiptInfoDAO;

public class ReceiptInfoDAOImpl implements ReceiptInfoDAO {

	@Autowired
	DataSource dataSource;
	
	@Override
	public void AddReceiptInfo(ReceiptInfo receiptinfo) {
		
		
		PreparedStatement  pstmt;
		Connection conn = null; 
				
		try {
			conn=dataSource.getConnection();
			String sqluser = "INSERT INTO receipt_info"
				    + "(image_id,receipt_id,purchase_date,"
				    + "purchase_time,chain_id,store_name,"
				    + "store_phone,store_phone2,"
				    + "address,total_amount,discount_description,"
				    + "discount,purchase_code,"
				    + "total_item,ocred_output) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt=conn.prepareStatement(sqluser,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, receiptinfo.getImageId());
			pstmt.setString(2, receiptinfo.getReceiptId());
			pstmt.setString(3, receiptinfo.getPurchaseDate());
			pstmt.setString(4, receiptinfo.getPurchaseTime());
			pstmt.setInt(5, receiptinfo.getChainId());
			pstmt.setString(6, receiptinfo.getStoreName());
			pstmt.setString(7, receiptinfo.getStorePhone());
			pstmt.setString(8, receiptinfo.getStorePhone2());
			pstmt.setString(9, receiptinfo.getAddress());
			pstmt.setString(10, receiptinfo.getTotalAmount());
			pstmt.setString(11, receiptinfo.getDiscountDescription());
			pstmt.setString(12, receiptinfo.getDiscount());
			pstmt.setString(13, receiptinfo.getPurchaseCode());
			pstmt.setString(14, receiptinfo.getTotalNoOfItem());
			pstmt.setString(15, receiptinfo.getOcredOutput());
			pstmt.executeUpdate();
			int receiptInfoId=0;
			ResultSet rs = null;
			rs = pstmt.getGeneratedKeys();
		
		    if (rs.next()) {
		    	receiptInfoId = rs.getInt(1);
		    } 
			pstmt.close();
			
			//code for add items
			String sqlItem = "INSERT INTO item"
				    + "(receipt_info_id,item_description,item_quantity,item_total,discount_description,discount) VALUES (?,?,?,?,?,?)";
			
			PreparedStatement  pstmtItem=conn.prepareStatement(sqlItem);
			if(receiptinfo.getItemList()!=null){
			for(Item item: receiptinfo.getItemList())
			{
				pstmtItem.setInt(1, receiptInfoId);
				pstmtItem.setString(2, item.getItemDescription());
				pstmtItem.setString(3, item.getItemQuantity());
				pstmtItem.setString(4, item.getItemTotal());
				pstmtItem.setString(5, item.getRawItemDiscountDesc());
				pstmtItem.setString(6, item.getRawItemDiscount());
				pstmtItem.executeUpdate();
			}
			}
			pstmtItem.close();
			//============
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void UpdateReceiptInfo(ReceiptInfo receiptinfo) {
		
		
		PreparedStatement  pstmt;
		Connection conn = null; 
				
		try {
			conn=dataSource.getConnection();
			String sqluser = "UPDATE receipt_info"
				    + " SET image_id=?,receipt_id=?,purchase_date=?,"
				    + "purchase_time=?,chain_id=?,store_name=?,"
				    + "store_phone=?,store_phone2=?,"
				    + "address=?,total_amount=?,discount_description=?,"
				    + "discount=?,purchase_code=?,"
				    + "total_item=?,ocred_output=? where receipt_info_id="+receiptinfo.getReceiptInfoId();
			
			pstmt=conn.prepareStatement(sqluser);
			pstmt.setInt(1, receiptinfo.getImageId());
			pstmt.setString(2, receiptinfo.getReceiptId());
			pstmt.setString(3, receiptinfo.getPurchaseDate());
			pstmt.setString(4, receiptinfo.getPurchaseTime());
			pstmt.setInt(5, receiptinfo.getChainId());
			pstmt.setString(6, receiptinfo.getStoreName());
			pstmt.setString(7, receiptinfo.getStorePhone());
			pstmt.setString(8, receiptinfo.getStorePhone2());
			pstmt.setString(9, receiptinfo.getAddress());
			pstmt.setString(10, receiptinfo.getTotalAmount());
			pstmt.setString(11, receiptinfo.getDiscountDescription());
			pstmt.setString(12, receiptinfo.getDiscount());
			pstmt.setString(13, receiptinfo.getPurchaseCode());
			
			pstmt.setString(14, receiptinfo.getTotalNoOfItem());
			pstmt.setString(15, receiptinfo.getOcredOutput());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ReceiptInfo getReceiptInfoByImageId(int imageId) {
		// TODO Auto-generated method stub

		Connection conn = null; 
		ReceiptInfo receiptInfo=new ReceiptInfo();
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * from receipt_info where image_id="+imageId;
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next()) {
				receiptInfo.setReceiptInfoId(rs.getInt("receipt_info_id"));
				receiptInfo.setReceiptId(rs.getString("receipt_id"));
				receiptInfo.setImageId(rs.getInt("image_id"));
				receiptInfo.setPurchaseDate(rs.getString("purchase_date"));
				receiptInfo.setAddress(rs.getString("address"));
				//receiptInfo.setChainId(chainId);
				receiptInfo.setDiscount(rs.getString("discount"));
				receiptInfo.setDiscountDescription(rs.getString("discount_description"));
				receiptInfo.setPurchaseTime(rs.getString("purchase_time"));
				receiptInfo.setPurchaseCode(rs.getString("purchase_code"));
				receiptInfo.setOcredOutput(rs.getString("ocred_output"));
				receiptInfo.setStoreName(rs.getString("store_name"));
				receiptInfo.setStorePhone(rs.getString("store_phone"));
				receiptInfo.setStorePhone2(rs.getString("store_phone2"));
				receiptInfo.setTotalAmount(rs.getString("total_amount"));
				receiptInfo.setTotalNoOfItem(rs.getString("total_item"));
				/*image.setStatus(rs.getString("stage_id"));*/
				
				
			}
			rs.close();
			
			
			String sql1 = "SELECT * from item where receipt_info_id="+receiptInfo.getReceiptInfoId();
			rs=stmt.executeQuery(sql1);
			List<Item> itemList=new ArrayList<Item>();
			while(rs.next()) {
				Item item=new Item();
				item.setItemId(rs.getInt("item_id"));
				item.setItemQuantity(rs.getString("item_quantity"));
				item.setItemTotal(rs.getString("item_total"));
				item.setRawItemDiscount(rs.getString("discount"));
				item.setRawItemDiscountDesc(rs.getString("discount_description"));
				item.setItemDescription(rs.getString("item_description"));
				/*image.setStatus(rs.getString("stage_id"));*/
				itemList.add(item);
				
			}
			receiptInfo.setItemList(itemList);
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return receiptInfo;
	}

	
}

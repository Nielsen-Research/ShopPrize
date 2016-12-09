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
import com.bean.KPI;
import com.dao.KpiDAO;

public class KpiDAOImpl implements KpiDAO {

	@Autowired
	DataSource dataSource;
	
	@Override
	public void createKpi(KPI kpi) {
		
		
		PreparedStatement  pstmt;
		Connection conn = null; 
				
		try {
			conn=dataSource.getConnection();
			String sqluser = "INSERT INTO kpi"
				    + "(image_id,chain_name,ocred,"
				    + "transcription,status,char_modified,"
				    + "rejection_reason1,rejection_reason2,"
				    + "rejection_reason3,other_reason,other_info,time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt=conn.prepareStatement(sqluser,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, kpi.getImageId());
			pstmt.setString(2, kpi.getChainName());
			pstmt.setString(3, kpi.isOcred());
			pstmt.setString(4, kpi.getTranscription());
			pstmt.setString(5, kpi.getStatus());
			pstmt.setInt(6, kpi.getModifiedChar());
		
			pstmt.setString(7, kpi.getRejectionReason1());
			pstmt.setString(8, kpi.getRejectionReason2());
			pstmt.setString(9, kpi.getRejectionReason3());
			pstmt.setString(10, kpi.getOtherReason());
			pstmt.setString(11, kpi.getOtherInfo());
			pstmt.setString(12, kpi.getTime());
		
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public KPI getKpi(int KpiId) {
		// TODO Auto-generated method stub
		Connection conn = null; 
		
		Statement stmt=null;;
		KPI kpi=new KPI();
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * from kpi where kpi_id="+KpiId;
			ResultSet rs=stmt.executeQuery(sql);  
			if (rs.next()) {
				kpi.setKpiId(rs.getInt("kpi_id"));
				kpi.setChainName(rs.getString("chain_name"));
				kpi.setImageId(rs.getInt("image_id"));
				kpi.setOcred(rs.getString("ocred"));
				kpi.setEnteredChar(rs.getInt("char_entered"));
				kpi.setModifiedChar(rs.getInt("char_modified"));
				kpi.setStatus(rs.getString("status"));
				kpi.setTime(rs.getString("time"));
				kpi.setRejectionReason1(rs.getString("rejection_reason1"));
				kpi.setRejectionReason2(rs.getString("rejection_reason2"));
				kpi.setRejectionReason3(rs.getString("rejection_reason3"));
				kpi.setOtherReason(rs.getString("other_reason"));
				kpi.setOtherInfo(rs.getString("other_info"));
				kpi.setTranscription(rs.getString("transcription"));
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kpi;
	}

	@Override
	public void updateKpi(KPI kpi) {
		
		PreparedStatement  pstmt;
		Connection conn = null; 
				
		try {
			conn=dataSource.getConnection();
			String sql = "UPDATE kpi"
				    + " SET image_id=?,chain_name=?,ocred=?,"
				    + "transcription=?,status=?,char_modified=?,"
				    + "rejection_reason1=?,rejection_reason2=?,"
				    + "rejection_reason3=?,other_reason=?,other_info=?,time=? where kpi_id="+kpi.getKpiId();
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, kpi.getImageId());
			pstmt.setString(2, kpi.getChainName());
			pstmt.setString(3, kpi.isOcred());
			pstmt.setString(4, kpi.getTranscription());
			pstmt.setString(5, kpi.getStatus());
			pstmt.setInt(6, kpi.getModifiedChar());
		
			pstmt.setString(7, kpi.getRejectionReason1());
			pstmt.setString(8, kpi.getRejectionReason2());
			pstmt.setString(9, kpi.getRejectionReason3());
			pstmt.setString(10, kpi.getOtherReason());
			pstmt.setString(11, kpi.getOtherInfo());
			pstmt.setString(12, kpi.getTime());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*@Override
	public List<KPI> getAllKPIByBatch(int batchId) {
		// TODO Auto-generated method stub
		Connection conn = null; 
		List<KPI> kpiList=new ArrayList<KPI>();
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * from kpi where batch_id="+batchId;
			ResultSet rs=stmt.executeQuery(sql);  
			if (rs.next()) {
				KPI kpi=new KPI();
				kpi.setKpiId(rs.getInt("kpi_id"));
				kpi.setChainName(rs.getString("chain_name"));
				kpi.setImageId(rs.getInt("image_id"));
				kpi.setOcred(rs.getString("ocred"));
				kpi.setModifiedChar(rs.getInt("char_modified"));
				kpi.setStatus(rs.getString("status"));
				kpi.setTime(rs.getString("time"));
				kpi.setRejectionReason1(rs.getString("rejection_reason1"));
				kpi.setRejectionReason2(rs.getString("rejection_reason2"));
				kpi.setRejectionReason3(rs.getString("rejection_reason3"));
				kpi.setOtherReason(rs.getString("other_reason"));
				kpi.setOtherInfo(rs.getString("other_info"));
				kpi.setTranscription(rs.getString("transcription"));
				kpiList.add(kpi);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kpiList;
	}
	
*/
	@Override
	public List<KPI> getAllKPI() {
		// TODO Auto-generated method stub
		Connection conn = null; 
		List<KPI> kpiList=new ArrayList<KPI>();
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * from kpi";
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next()) {
				KPI kpi=new KPI();
				kpi.setKpiId(rs.getInt("kpi_id"));
				kpi.setChainName(rs.getString("chain_name"));
				kpi.setImageId(rs.getInt("image_id"));
				kpi.setOcred(rs.getString("ocred"));
				kpi.setEnteredChar(rs.getInt("char_entered"));
				kpi.setModifiedChar(rs.getInt("char_modified"));
				kpi.setStatus(rs.getString("status"));
				kpi.setTime(rs.getString("time"));
				kpi.setRejectionReason1(rs.getString("rejection_reason1"));
				kpi.setRejectionReason2(rs.getString("rejection_reason2"));
				kpi.setRejectionReason3(rs.getString("rejection_reason3"));
				kpi.setOtherReason(rs.getString("other_reason"));
				kpi.setOtherInfo(rs.getString("other_info"));
				kpi.setTranscription(rs.getString("transcription"));
				kpiList.add(kpi);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kpiList;
	}

	@Override
	public List<KPI> getAllKPIByBatch(int batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOcredCount() {
		// TODO Auto-generated method stub
		Connection conn = null; 
		
		Statement stmt=null;;
		int count=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT count(*) from kpi where ocred='YES'";
			ResultSet rs=stmt.executeQuery(sql);  
			if(rs.next()) {
				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getApprovedImageCount() {
		// TODO Auto-generated method stub
Connection conn = null; 
		
		Statement stmt=null;;
		int count=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT count(*) from kpi where status='APPROVED'";
			ResultSet rs=stmt.executeQuery(sql);  
			if(rs.next()) {
				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getRejectedImageCount() {
		// TODO Auto-generated method stub
Connection conn = null; 
		
		Statement stmt=null;;
		int count=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT count(*) from kpi where status='REJECTED'";
			ResultSet rs=stmt.executeQuery(sql);  
			if(rs.next()) {
				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getChainNameCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFullTranscriptionCount() {
		// TODO Auto-generated method stub
		Connection conn = null; 
		
		Statement stmt=null;;
		int count=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT count(*) from kpi where transcription='FULL'";
			ResultSet rs=stmt.executeQuery(sql);  
			if(rs.next()) {
				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getPartialTranscriptionCount() {
		// TODO Auto-generated method stub
Connection conn = null; 
		
		Statement stmt=null;;
		int count=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT count(*) from kpi where transcription='PARTIAL'";
			ResultSet rs=stmt.executeQuery(sql);  
			if(rs.next()) {
				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getTotalImageCount() {
		// TODO Auto-generated method stub
Connection conn = null; 
		
		Statement stmt=null;;
		int count=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT count(*) from kpi";
			ResultSet rs=stmt.executeQuery(sql);  
			if(rs.next()) {
				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getBadImageCount() {
		// TODO Auto-generated method stub
Connection conn = null; 
		
		Statement stmt=null;;
		int count=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT count(*) from kpi a, image b where a.image_id = b.image_id and b.classification = 'BAD'";
			ResultSet rs=stmt.executeQuery(sql);  
			if(rs.next()) {
				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getGoodImageCount() {
		// TODO Auto-generated method stub
Connection conn = null; 
		
		Statement stmt=null;;
		int count=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT count(*) from kpi a, image b where a.image_id = b.image_id and b.classification = 'GOOD'";
			ResultSet rs=stmt.executeQuery(sql);  
			if(rs.next()) {
				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getCFCount(float start, float end) {
		// TODO Auto-generated method stub
		Connection conn = null; 
		Statement stmt=null;;
		int count=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT count(*) FROM kpi where IFNULL((char_entered - char_modified)/char_entered,0) >"+start+" and  IFNULL((char_entered - char_modified)/char_entered,0) <= "+end+" and ocred = 'YES'";
			ResultSet rs=stmt.executeQuery(sql);  
			if(rs.next()) {
				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getChainIdentifiedCount() {
		// TODO Auto-generated method stub
Connection conn = null; 
		
		Statement stmt=null;;
		int count=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "select count(*) from kpi where chain_name is not null and classification = 'GOOD'";
			ResultSet rs=stmt.executeQuery(sql);  
			if(rs.next()) {
				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}

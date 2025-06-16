package like.service;

import java.sql.SQLException;

import like.dao.LikeDAO;

public class LikeService {

	LikeDAO likeDAO = new LikeDAO();
	
	public boolean likePost(String id, int bid) throws SQLException {
		if (alreadyLiked(id, bid)) {
			return false;
		}
		return likeDAO.createLike(id, bid);
	}

	public boolean alreadyLiked(String id, int bid) throws SQLException {
		return likeDAO.findLike(id, bid);
	}

	public int countLike(int bid) throws SQLException {
		return likeDAO.countLike(bid);
	}

}

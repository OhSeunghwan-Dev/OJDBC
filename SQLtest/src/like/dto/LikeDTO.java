package like.dto;

import java.util.Objects;

public class LikeDTO {

	private int bid;
	private String likeUser;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getLikeUser() {
		return likeUser;
	}
	public void setLikeUser(String likeUser) {
		this.likeUser = likeUser;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bid, likeUser);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LikeDTO other = (LikeDTO) obj;
		return bid == other.bid && Objects.equals(likeUser, other.likeUser);
	}
}

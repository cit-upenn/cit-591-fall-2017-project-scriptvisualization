package script;

/**
 * This enum class represents all genres available
 * @author yueyin
 *
 */
public enum Genre {
	
	ACTION,ADVENTURE, ANIMATION, COMEDY, CRIME, DRAMA, FAMILY, FANTASY, 
	FILMNOIR{
		public String toString() {
			return "FILM-NOIR";
		}
	}
	, HORROR, MUSICAL, MYSTERY, ROMANCE, 
	SCIFI{
		public String toString() {
			return "SCI-FI";
		}
	},SHORT, THRILLER,WAR,WESTERN

}

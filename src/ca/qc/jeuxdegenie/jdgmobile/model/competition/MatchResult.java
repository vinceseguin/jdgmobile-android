package ca.qc.jeuxdegenie.jdgmobile.model.competition;

/**
 * 
 */
public class MatchResult {

	private int matchNumber;
	private String startTime;
	private String matchLocation;
	private int roundId;
	private String roundDescription;
	private int matchStatusId;
	private String homeSchool;
	private String awaySchool;
	private int homeResult;
	private int awayResult;
	
	/**
	 * 
	 * @param matchNumber
	 * @param startTime
	 * @param matchLocation
	 * @param roundId
	 * @param roundDescription
	 * @param matchStatusId
	 * @param homeSchool
	 * @param awaySchool
	 * @param homeResult
	 * @param awayResult
	 */
	public MatchResult(int matchNumber, String startTime, String matchLocation, int roundId, 
					   String roundDescription, int matchStatusId, String homeSchool, 
					   String awaySchool, int homeResult, int awayResult) {
		this.matchNumber = matchNumber;
		this.startTime = startTime;
		this.matchLocation = matchLocation;
		this.roundId = roundId;
		this.roundDescription = roundDescription;
		this.matchStatusId = matchStatusId;
		this.homeSchool = homeSchool;
		this.awaySchool = awaySchool;
		this.homeResult = homeResult;
		this.awayResult = awayResult;
	}

	/**
	 * @return the matchNumber
	 */
	public int getMatchNumber() {
		return matchNumber;
	}

	/**
	 * @param matchNumber the matchNumber to set
	 */
	public void setMatchNumber(int matchNumber) {
		this.matchNumber = matchNumber;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the matchLocation
	 */
	public String getMatchLocation() {
		return matchLocation;
	}

	/**
	 * @param matchLocation the matchLocation to set
	 */
	public void setMatchLocation(String matchLocation) {
		this.matchLocation = matchLocation;
	}

	/**
	 * @return the roundId
	 */
	public int getRoundId() {
		return roundId;
	}

	/**
	 * @param roundId the roundId to set
	 */
	public void setRoundId(int roundId) {
		this.roundId = roundId;
	}

	/**
	 * @return the roundDescription
	 */
	public String getRoundDescription() {
		return roundDescription;
	}

	/**
	 * @param roundDescription the roundDescription to set
	 */
	public void setRoundDescription(String roundDescription) {
		this.roundDescription = roundDescription;
	}

	/**
	 * @return the matchStatusId
	 */
	public int getMatchStatusId() {
		return matchStatusId;
	}

	/**
	 * @param matchStatusId the matchStatusId to set
	 */
	public void setMatchStatusId(int matchStatusId) {
		this.matchStatusId = matchStatusId;
	}

	/**
	 * @return the homeSchool
	 */
	public String getHomeSchool() {
		return homeSchool;
	}

	/**
	 * @param homeSchool the homeSchool to set
	 */
	public void setHomeSchool(String homeSchool) {
		this.homeSchool = homeSchool;
	}

	/**
	 * @return the awaySchool
	 */
	public String getAwaySchool() {
		return awaySchool;
	}

	/**
	 * @param awaySchool the awaySchool to set
	 */
	public void setAwaySchool(String awaySchool) {
		this.awaySchool = awaySchool;
	}

	/**
	 * @return the homeResult
	 */
	public int getHomeResult() {
		return homeResult;
	}

	/**
	 * @param homeResult the homeResult to set
	 */
	public void setHomeResult(int homeResult) {
		this.homeResult = homeResult;
	}

	/**
	 * @return the awayResult
	 */
	public int getAwayResult() {
		return awayResult;
	}

	/**
	 * @param awayResult the awayResult to set
	 */
	public void setAwayResult(int awayResult) {
		this.awayResult = awayResult;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package database;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Robert
 */


/* all methods currently are static in the sqlServer class from Kasey */

public class DBTest {
    
    
    
    public static void main(String args[]){
        
        sqlServer.CreateDatabase();
    
        sqlServer.testClearCommLog();
        sqlServer.testClearRAD();
        sqlServer.testClearSAR();
        
        long date = new Date().getTime();
        int newid = sqlServer.RetrieveNextFormId();
        int next = sqlServer.RetrieveNextFormId();
        String misnum = "65";
        String misnum2 = "4";
        System.out.printf("misnum=%d, next=%d\n", newid, next);
        String json = "{\"type\":\"SearchAndRescue\",\"name\":\"SAR form yee\",\"header\":{\"missionNumber\":\"12312\",\"activityForDateOf\":\"23423\",\"reportedBy\":\"khjhjgj\",\"dateTime\":\"hgjhg\"},\"alpha\":{\"nameOfSearchOrg1\":\"jhgjh\",\"nameOfSearchOrg2\":\"gjhg\",\"nameOfSearchOrg3\":\"jh\"},\"bravo\":{\"timeDispatched1\":\"gj\",\"timeDispatched2\":\"hg\",\"timeDispatched3\":\"bvjf\",\"timeELTFirstHeard1\":\"tv\",\"timeELTFirstHeard2\":\"yr\",\"timeELTFirstHeard3\":\"evy\",\"numAircraft1\":\"erdvbnm\",\"numAircraft2\":\"kjng\",\"numAircraft3\":\"iubtv6\",\"numSorties1\":\"4e\",\"numSorties2\":\"vbdrnf\",\"numSorties3\":\" yg\",\"hoursInSearchArea1\":\" yuf\",\"hoursInSearchArea2\":\"8t7f\",\"hoursInSearchArea3\":\"ugyuyfyte6\",\"hoursEnroute1\":\"54ev6\",\"hoursEnroute2\":\"5r\",\"hoursEnroute3\":\"6c546\",\"totalFlightHours1\":\"535\",\"totalFlightHours2\":\"46\",\"totalFlightHours3\":\"78\",\"totalPersonnel1\":\"99\",\"totalPersonnel2\":\"87654\",\"totalPersonnel3\":\"345678\",\"areaSearch\":[[\"7654765\",\"567\",\"6\",\"78\",\"76567\"],[\"5667876\",\"8\",\"7898\",\"76\",\"876\"],[\"789\",\"765\",\"7656\",\"7876\",\"567\"],[\"876\",\"678\",\"7876\",\"78\",\"65678\"]],\"other\":\"dsfdfgdfg\",\"significantWeather\":\"sdfsdf\"},\"charlie\":{\"totalResourcesExpectedACPT\":\"kjhkh\",\"totalResourcesExpectedPersonnel\":\"kjhk\",\"plannedSearchArea\":\"kjhkj\",\"forcastedWeather\":\"dsfsdfsdfssdf\"},\"delta\":{\"nameOfOrg\":\"sdfs\",\"actualLoc\":\"kghjhg\",\"coordinates\":\"jhgjhg\",\"timeObjectiveLocated\":\"jhgjh\",\"ELT\":\"g\",\"BY\":\"mhkhj\",\"terrainAndGroundCover\":\"kjh\",\"numSubjectsInvolved\":\"kjhk\",\"numAlive\":\"jhk\",\"numDeceased\":\"jhk\",\"numMissing\":\"jh\"},\"echo\":{\"orgMakingRecovery\":\"kjh\",\"timeRecoveryBegan\":\"kjh\",\"subjectsDeliveredTo\":\"hk\",\"timeRecoveryCompleted\":\"kj\",\"recoveryMethods\":\"hk\",\"numRecoveredAlive\":\"jh\",\"numRecoveredDeceased\":\"kjh\",\"numSelfRecovered\":\"kj\"},\"foxtrot\":{\"numSubjectsSaved\":\"jhk\",\"numSubjectsAssisted\":\"jh\",\"organizationSavesCreditedTo\":\"kjh\",\"missionClosed\":false,\"missionSuspended\":true,\"closeOrSuspendTime\":\"k\"},\"golf\":{\"additionalRemarks\":\"sdfsdf\"}}";
        
        sqlServer.InsertCommLog(json,newid, misnum, 100000);
        List result = sqlServer.SelectFromCommLogWithDate(100000);
        sqlServer.InsertCommLog(json, next, misnum2, date);
        try{    
            
                //System.out.printf("id:%d\nmission:%d\ndate:%s\nJSON:%s\n",result.getInt("COMMID"),result.getInt("MISSIONNUMBER"), result.getString("DATE"), result.getString("JSONDATA"));
            
        }catch(Exception e){
           System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

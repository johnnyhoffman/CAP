import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/* Placeholder for demonstrating Session MVC */
public class SearchAndRescueController implements Controller {

    private SearchAndRescueView view;
    private SearchAndRescueModel model;

    public SearchAndRescueController(String name) {
        view = new SearchAndRescueView();
        view.setName(name);
        model = new SearchAndRescueModel(name);
        setListeners();
    }

    @Override
    public Component getViewComponent() {
        /*
         * Needed a way to wrap the view in a scrollPane, not sure if any
         * repercussions of this
         */
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(view);
        scroll.setName(view.getName());
        return scroll; // this function only returned view to start with
    }

    public void setName(String name) {
        view.setName(name);
    }

    /*
     * Set the listeners for all of the actions we want to keep track of in the
     * view.
     */
    public void setListeners() {
        // TODO: Add change listeners for "bravo.areaSearch" fields
        view.addHeaderMissionNumberChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateHeaderMissionNumber(view
                        .getHeaderMissionNumberText());
            }
        });
        view.addHeaderActivityForDateOfChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateHeaderActivityForDateOf(view
                        .getHeaderActivityForDateOfText());
            }
        });
        view.addHeaderReportedByChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateHeaderReportedBy(view.getHeaderReportedByText());
            }
        });
        view.addHeaderDateTimeChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateHeaderDateTime(view.getHeaderDateTimeText());
            }
        });
        view.addAlphaNameOfSearchOrg1ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateAlphaNameOfSearchOrg1(view
                        .getAlphaNameOfSearchOrg1Text());
            }
        });
        view.addAlphaNameOfSearchOrg2ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateAlphaNameOfSearchOrg2(view
                        .getAlphaNameOfSearchOrg2Text());
            }
        });
        view.addAlphaNameOfSearchOrg3ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateAlphaNameOfSearchOrg3(view
                        .getAlphaNameOfSearchOrg3Text());
            }
        });
        view.addBravoTimeDispatched1ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoTimeDispatched1(view
                        .getBravoTimeDispatched1Text());
            }
        });
        view.addBravoTimeDispatched2ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoTimeDispatched2(view
                        .getBravoTimeDispatched2Text());
            }
        });
        view.addBravoTimeDispatched3ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoTimeDispatched3(view
                        .getBravoTimeDispatched3Text());
            }
        });
        view.addBravoTimeELTFirstHeard1ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoTimeELTFirstHeard1(view
                        .getBravoTimeELTFirstHeard1Text());
            }
        });
        view.addBravoTimeELTFirstHeard2ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoTimeELTFirstHeard2(view
                        .getBravoTimeELTFirstHeard2Text());
            }
        });
        view.addBravoTimeELTFirstHeard3ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoTimeELTFirstHeard3(view
                        .getBravoTimeELTFirstHeard3Text());
            }
        });
        view.addBravoNumAircraft1ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoNumAircraft1(view.getBravoNumAircraft1Text());
            }
        });
        view.addBravoNumAircraft2ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoNumAircraft2(view.getBravoNumAircraft2Text());
            }
        });
        view.addBravoNumAircraft3ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoNumAircraft3(view.getBravoNumAircraft3Text());
            }
        });
        view.addBravoNumSorties1ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoNumSorties1(view.getBravoNumSorties1Text());
            }
        });
        view.addBravoNumSorties2ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoNumSorties2(view.getBravoNumSorties2Text());
            }
        });
        view.addBravoNumSorties3ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoNumSorties3(view.getBravoNumSorties3Text());
            }
        });
        view.addBravoHoursInSearchArea1ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoHoursInSearchArea1(view
                        .getBravoHoursInSearchArea1Text());
            }
        });
        view.addBravoHoursInSearchArea2ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoHoursInSearchArea2(view
                        .getBravoHoursInSearchArea2Text());
            }
        });
        view.addBravoHoursInSearchArea3ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoHoursInSearchArea3(view
                        .getBravoHoursInSearchArea3Text());
            }
        });
        view.addBravoHoursEnroute1ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoHoursEnroute1(view.getBravoHoursEnroute1Text());
            }
        });
        view.addBravoHoursEnroute2ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoHoursEnroute2(view.getBravoHoursEnroute2Text());
            }
        });
        view.addBravoHoursEnroute3ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoHoursEnroute3(view.getBravoHoursEnroute3Text());
            }
        });
        view.addBravoTotalFlightHours1ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoTotalFlightHours1(view
                        .getBravoTotalFlightHours1Text());
            }
        });
        view.addBravoTotalFlightHours2ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoTotalFlightHours2(view
                        .getBravoTotalFlightHours2Text());
            }
        });
        view.addBravoTotalFlightHours3ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoTotalFlightHours3(view
                        .getBravoTotalFlightHours3Text());
            }
        });
        view.addBravoTotalPersonnel1ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoTotalPersonnel1(view
                        .getBravoTotalPersonnel1Text());
            }
        });
        view.addBravoTotalPersonnel2ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoTotalPersonnel2(view
                        .getBravoTotalPersonnel2Text());
            }
        });
        view.addBravoTotalPersonnel3ChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoTotalPersonnel3(view
                        .getBravoTotalPersonnel3Text());
            }
        });
        view.addBravoOtherChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoOther(view.getBravoOtherText());
            }
        });
        view.addBravoSignificantWeatherChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateBravoSignificantWeather(view
                        .getBravoSignificantWeatherText());
            }
        });
        view.addCharlieTotalResourcesExpectedACPTChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateCharlieTotalResourcesExpectedACPT(view
                        .getCharlieTotalResourcesExpectedACPTText());
            }
        });
        view.addCharlieTotalResourcesExpectedPersonnelChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateCharlieTotalResourcesExpectedPersonnel(view
                        .getCharlieTotalResourcesExpectedPersonnelText());
            }
        });
        view.addCharliePlannedSearchAreaChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateCharliePlannedSearchArea(view
                        .getCharliePlannedSearchAreaText());
            }
        });
        view.addCharlieForcastedWeatherChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateCharlieForcastedWeather(view
                        .getCharlieForcastedWeatherText());
            }
        });
        view.addDeltaNameOfOrgChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDeltaNameOfOrg(view.getDeltaNameOfOrgText());
            }
        });

        view.addDeltaActualLocChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDeltaActualLoc(view.getDeltaActualLocText());
            }
        });

        view.addDeltaCoordinatesChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDeltaCoordinates(view.getDeltaCoordinatesText());
            }
        });

        view.addDeltaTimeObjectiveLocatedChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDeltaTimeObjectiveLocated(view
                        .getDeltaTimeObjectiveLocatedText());
            }
        });

        view.addDeltaELTChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDeltaELT(view.getDeltaELTText());
            }
        });

        view.addDeltaBYChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDeltaBY(view.getDeltaBYText());
            }
        });

        view.addDeltaTerrainAndGroundCoverChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDeltaTerrainAndGroundCover(view
                        .getDeltaTerrainAndGroundCoverText());
            }
        });

        view.addDeltaNumSubjectsInvolvedChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDeltaNumSubjectsInvolved(view
                        .getDeltaNumSubjectsInvolvedText());
            }
        });

        view.addDeltaNumAliveChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDeltaNumAlive(view.getDeltaNumAliveText());
            }
        });

        view.addDeltaNumDeceasedChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDeltaNumDeceased(view.getDeltaNumDeceasedText());
            }
        });

        view.addDeltaNumMissingChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDeltaNumMissing(view.getDeltaNumMissingText());
            }
        });

        view.addEchoOrgMakingRecoveryChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoOrgMakingRecovery(view
                        .getEchoOrgMakingRecoveryText());
            }
        });

        view.addEchoTimeRecoveryBeganChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoTimeRecoveryBegan(view
                        .getEchoTimeRecoveryBeganText());
            }
        });

        view.addEchoSubjectsDeliveredToChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoSubjectsDeliveredTo(view
                        .getEchoSubjectsDeliveredToText());
            }
        });

        view.addEchoTimeRecoveryCompletedChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoTimeRecoveryCompleted(view
                        .getEchoTimeRecoveryCompletedText());
            }
        });

        view.addEchoRecoveryMethodsChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoRecoveryMethods(view
                        .getEchoRecoveryMethodsText());
            }
        });

        view.addEchoNumRecoveredAliveChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoNumRecoveredAlive(view
                        .getEchoNumRecoveredAliveText());
            }
        });

        view.addEchoNumRecoveredDeceasedChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoNumRecoveredDeceased(view
                        .getEchoNumRecoveredDeceasedText());
            }
        });

        view.addEchoNumSelfRecoveredChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoNumSelfRecovered(view
                        .getEchoNumSelfRecoveredText());
            }
        });

        view.addFoxtrotNumSubjectsSavedChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateFoxtrotNumSubjectsSaved(view
                        .getFoxtrotNumSubjectsSavedText());
            }
        });

        view.addFoxtrotNumSubjectsAssistedChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateFoxtrotNumSubjectsAssisted(view
                        .getFoxtrotNumSubjectsAssistedText());
            }

        });

        view.addFoxtrotMissionClosedActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.updateFoxtrotMissionClosedOrSuspended(
                        view.getFoxtrotMissionClosed(),
                        view.getFoxtrotMissionSuspended());
            }
        });

        view.addFoxtrotMissionSuspendedActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.updateFoxtrotMissionClosedOrSuspended(
                        view.getFoxtrotMissionClosed(),
                        view.getFoxtrotMissionSuspended());
            }
        });

        view.addFoxtrotOrganizationSavesCreditedToChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateFoxtrotOrganizationSavesCreditedTo(view
                        .getFoxtrotOrganizationSavesCreditedToText());
            }
        });

        view.addFoxtrotCloseOrSuspendTimeChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateFoxtrotCloseOrSuspendTime(view
                        .getFoxtrotCloseOrSuspendTimeText());
            }
        });

        view.addAdditionalRemarksChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateGolfAdditionalRemarks(view
                        .getAdditionalRemarksText());
            }
        });
    }
}
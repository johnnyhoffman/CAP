import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/* Placeholder for demonstrating Session MVC */
public class LocatingDataController implements Controller {

    private LocatingDataView view;
    private LocatingDataModel model;

    public LocatingDataController(String name) {
        view = new LocatingDataView();
        view.setName(name);
        model = new LocatingDataModel(name);
        setListeners();
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    /*
     * Set the listeners for all of the actions we want to keep track of in the
     * view.
     */
    public void setListeners() {
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
                model.updateDeltaTimeObjectiveLocated(view.getDeltaTimeObjectiveLocatedText());
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
                model.updateDeltaTerrainAndGroundCover(view.getDeltaTerrainAndGroundCoverText());
            }
        });

        view.addDeltaNumSubjectsInvolvedChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDeltaNumSubjectsInvolved(view.getDeltaNumSubjectsInvolvedText());
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
                model.updateEchoOrgMakingRecovery(view.getEchoOrgMakingRecoveryText());
            }
        });

        view.addEchoTimeRecoveryBeganChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoTimeRecoveryBegan(view.getEchoTimeRecoveryBeganText());
            }
        });

        view.addEchoSubjectsDeliveredToChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoSubjectsDeliveredTo(view.getEchoSubjectsDeliveredToText());
            }
        });

        view.addEchoTimeRecoveryCompletedChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoTimeRecoveryCompleted(view.getEchoTimeRecoveryCompletedText());
            }
        });

        view.addEchoRecoveryMethodsChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoRecoveryMethods(view.getEchoRecoveryMethodsText());
            }
        });

        view.addEchoNumRecoveredAliveChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoNumRecoveredAlive(view.getEchoNumRecoveredAliveText());
            }
        });

        view.addEchoNumRecoveredDeceasedChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoNumRecoveredDeceased(view.getEchoNumRecoveredDeceasedText());
            }
        });

        view.addEchoNumSelfRecoveredChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateEchoNumSelfRecovered(view.getEchoNumSelfRecoveredText());
            }
        });

        view.addFoxtrotNumSubjectsSavedChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateFoxtrotNumSubjectsSaved(view.getFoxtrotNumSubjectsSavedText());
            }
        });

        view.addFoxtrotNumSubjectsAssistedChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateFoxtrotNumSubjectsAssisted(view.getFoxtrotNumSubjectsAssistedText());
            }

        });

        view.addFoxtrotMissionClosedActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.updateFoxtrotMissionClosedOrSuspended(view.getFoxtrotMissionClosed(),
                        view.getFoxtrotMissionSuspended());
            }
        });

        view.addFoxtrotMissionSuspendedActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.updateFoxtrotMissionClosedOrSuspended(view.getFoxtrotMissionClosed(),
                        view.getFoxtrotMissionSuspended());
            }
        });

        view.addFoxtrotOrganizationSavesCreditedToChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateFoxtrotOrganizationSavesCreditedTo(view.getFoxtrotOrganizationSavesCreditedToText());
            }
        });

        view.addFoxtrotCloseOrSuspendTimeChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateFoxtrotCloseOrSuspendTime(view.getFoxtrotCloseOrSuspendTimeText());
            }
        });

        view.addAdditionalRemarksChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateGolfAdditionalRemarks(view.getAdditionalRemarksText());
            }
        });
    }
}
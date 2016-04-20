package mxh.kickassmenu.menucontent;


import mxh.kickassmenu.menucontent.sectionPlate.MaterialSection;

/**
 * Created by marc on 08.03.2015.
 * edited by jjHesk 2015 June 21
 */
public interface MaterialSectionChangeListener {
    public void onBeforeChangeSection(MaterialSection newSection);

    public void onAfterChangeSection(MaterialSection newSection);
}

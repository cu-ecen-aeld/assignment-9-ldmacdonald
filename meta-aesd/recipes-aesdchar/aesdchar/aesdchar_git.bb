# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "gitsm://github.com/cu-ecen-aeld/assignments-3-and-later-ldmacdonald;protocol=https;branch=main \
           file://aesdchar-start-stop \
          "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "6d57e783395b77f165508e9eb0e330a93644f3e8"

S = "${WORKDIR}/git/aesd-char-driver"

inherit module

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdchar-start-stop"

do_install () {
	   install -d ${D}${bindir}
	   install -m 0755 ${S}/aesdchar_load ${D}${bindir}
	   install -m 0755 ${S}/aesdchar_unload ${D}${bindir}

	   install -d ${D}${sysconfdir}/init.d
	   install -m 0755 ${WORKDIR}/aesdchar-start-stop ${D}${sysconfdir}/init.d

	   install -d ${D}/lib/modules/${KERNEL_VERSION}/extra
	   install -m 0755 ${S}/aesdchar.ko ${D}/lib/modules/${KERNEL_VERSION}/extra	   
}

FILES:${PN} += " \
	       ${bindir}/aesdchar_load \
	       ${bindir}/aesdchar_unload \
	       ${sysconfdir}/init.d/aesdchar-start-stop \
	       "
	       
# need to inhibit package strip else insmod complains about .ko file being stripped
INHIBIT_PACKAGE_STRIP = "1"

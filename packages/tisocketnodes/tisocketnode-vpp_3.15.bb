PRIORITY = "optional"
DESCRIPTION = "Texas Instruments VPP Socket Node."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage \
	   tisocketnode-ringio \
	   tisocketnode-usn"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/video/node/vpp/... DSP-MM-TID-VIDEO_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/vpp"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

ENV_VAR = "DEPOT=${STAGING_BINDIR}/dspbridge/tools \
	   DSPMAKEROOT=${S}/make \
	   DBS_BRIDGE_DIR_C64=${STAGING_BINDIR}/dspbridge/dsp \
	   DBS_SABIOS_DIR_C64=${STAGING_BINDIR}/dspbridge/tools \
	   DBS_CGTOOLS_DIR_C64=${STAGING_BINDIR}/dspbridge/tools/cgt6x-6.0.7 \
	   DBS_FC=${STAGING_BINDIR}/dspbridge/dsp/bdsptools/framework_components_1_10_04/packages-bld \
	   DLLCREATE_DIR=${STAGING_BINDIR}/DLLcreate \
"

#set to release or debug
RELEASE = "release"

inherit ccasefetch

do_compile() {
	mkdir -p ${S}/system/utils
	cp -a ${STAGING_BINDIR}/dspbridge/system/utils/* ${S}/system/utils
	mkdir -p ${S}/system/usn
	cp -a ${STAGING_BINDIR}/dspbridge/system/usn/* ${S}/system/usn
	mkdir -p ${S}/system/inst2
        cp -a ${STAGING_BINDIR}/dspbridge/system/inst2/* ${S}/system/inst2
	mkdir -p ${S}/video/alg/vgpop
	cp -a ${STAGING_BINDIR}/dspbridge/video/alg/vgpop/* ${S}/video/alg/vgpop
## Getting MasterConfig files
        mkdir -p ${S}/include
        cp -a ${STAGING_INCDIR}/dspbridge/include/* ${S}/include
        mkdir -p ${S}/audio/alg/SampleRateConverter
        cp -a ${STAGING_BINDIR}/dspbridge/audio/alg/SampleRateConverter/* ${S}/audio/alg/SampleRateConverter
## Getting the dsp make system
        mkdir -p ${S}/make
        cp -a ${STAGING_BINDIR}/dspbridge/make/* ${S}/make
## Setting PATH for gmake
        pathorig=$PATH
        export PATH=$PATH:${STAGING_BINDIR}/dspbridge/tools/xdctools
        chmod -R +w ${S}/*
	cd ${S}/video/node/vpp
	sed -e 's%\\%\/%g' makefile > makefile.linux
	${ENV_VAR} oe_runmake -f makefile.linux build=omap3430${RELEASE}
	export PATH=$pathorig
        unset pathorig
}

do_install() {
	install -d ${D}${base_libdir}/dsp
	install -m 0644 ${S}/video/node/vpp/out/omap3430/${RELEASE}/vpp_sn.dll64P ${D}${base_libdir}/dsp
}
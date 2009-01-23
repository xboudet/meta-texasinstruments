DESCRIPTION = "D-Bus service providing high-level OBEX client and server side functionality"
LICENSE = "GPL"
PR = "r1"
DEFAULT_PREFERENCE = "-1"

DEPENDS = "dbus-glib dbus openobex bluez-libs"

SRC_URI = "http://tadas.dailyda.com/software/obex-data-server-${PV}.tar.gz \
          "
 
S = "${WORKDIR}/obex-data-server-${PV}"

EXTRA_OECONF = "--with-bluez-libs=${STAGING_LIBDIR} --with-bluez-includes=${STAGING_INCDIR}"

inherit autotools pkgconfig gconf

#FILES_${PN} += "\
#	/usr/*"


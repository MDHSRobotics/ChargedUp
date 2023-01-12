
package frc.robot.devices;

import frc.robot.consoles.Logger;

class Monitor {

    private String m_devName;
    private String m_devDescription;

    private String m_lastPrinted = "";
    private int m_nSkippedDuplicates = 0;

    Monitor(String devName, String devDescription) {
        m_devName = devName;
        m_devDescription = devDescription;
    }

    public void log(String methodName, String... args) {
        String methodSignature = getMethodSignature(methodName, args);
        if (!methodSignature.equals(m_lastPrinted)) {
            // Print number of duplicate method signatures that were not printed
            if (m_nSkippedDuplicates > 0) {
                String skip = String.format("(%s) %s.%s: (Skipped %d)", m_devDescription,
                                                                        m_devName,
                                                                        m_lastPrinted,
                                                                        m_nSkippedDuplicates);
                Logger.debug(skip);
                m_nSkippedDuplicates = 0;
            }
            String msg = String.format("(%s) %s.%s", m_devDescription, m_devName, methodSignature);
            Logger.debug(msg);

            m_lastPrinted = methodSignature;
        } else {
            ++m_nSkippedDuplicates;
        }
    }

    private String getMethodSignature(String methodName, String... args) {
        String argStr = "";
        for (String arg : args) {
            if (argStr == "") {
                argStr = arg;
                continue;
            }
            argStr = String.format("%s, %s", argStr, arg);
        }
        return String.format("%s(%s)", methodName, argStr);
    }

}

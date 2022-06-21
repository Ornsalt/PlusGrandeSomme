/**
 * PlusGrandeSomme.java :
 * A compilation of methods to calculate the greatest sum.
 * 
 * @author: Anthony ASCOET et Clément MONFORT.
 */
class PlusGrandeSomme {
    /**
     * ORDINAL COUNTER
     */
    long cpt = 0;

    /**
     * Convert a integrer array in a String object.
     * 
     * @param tab The array of integrer to convert has string object.
     * @return A String containing ascii version of a array of integer.
     * @author: Clément MONFORT.
     */
    String tabToStr(int[] tab) {
        String str = "{";

        if (tab != null) {
            for (int i = 0; i < tab.length; i++) {
                if (i < tab.length - 1) {
                    str += tab[i] + ", ";
                } else {
                    str += tab[i];
                }
            }
        }
        str += "}";
        return (str);
    }

    /**
     * Check if an array of integer contain only negative number.
     * 
     * @param tab The array of integrer to check.
     * @return A boolean, true if the full array is negative false otherwise.
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    boolean TabIsNeg(int[] tab) {
        boolean neg = true;

        for (int i = 0; neg && i < tab.length; i++) {
            if (tab[i] >= 0) {
                neg = false;
            }
        }
        return (neg);
    }

    /**
     * Find the greatest sum of an integer array in an interval.
     * 
     * @param tab The array of integer which we must find the greatest sum.
     * @param sum The array of integer which contain the greatest sum and is
     *            interval.
     * @param deb An integer, the start of the interval.
     * @param end An integer, the end of the interval.
     * @return An array of integer in this format {sum, debut, end}.
     * @author: Clément MONFORT.
     */
    void findGreatestSumInterval(int[] tab, int[] sum, int deb, int end) {
        int buf = 0;

        for (int i = deb; i <= end; i++) {
            buf += tab[i];
            if (buf > sum[0]) {
                sum[0] = buf;
                sum[1] = deb;
                sum[2] = i;
            }
            cpt++;
        }
    }

    /**
     * Find the greatest sum of an integer array.
     * 
     * @param tab The array of integer which we must find the greatest sum.
     * @return An array of integer in this format {sum, debut, end}, or null in case
     *         of error.
     * @author: Clément MONFORT.
     */
    int[] plusGrdeSomme1(int tab[]) {
        int[] sum = (tab == null) ? null : new int[] { 0, 0, 0 };

        if (sum != null && !TabIsNeg(tab)) {
            sum[0] = tab[0];
            for (int i = 0; i < tab.length; i++) {
                for (int j = i; j < tab.length; j++) {
                    findGreatestSumInterval(tab, sum, i, j);
                }
            }
        } else if (tab == null) {
            System.err.println("Error: The integer array is null.");
        }
        return (sum);
    }

    /**
     * test a call of the Methode plusGrdeSomme1.
     * 
     * @param tab - An array of integer in this format {sum, debut, end}, or null in
     *            case of error.
     * @param res - The result expected.
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    void testCasplusGrdeSomme1(int[] tab, int[] res) {
        int[] exec = plusGrdeSomme1(tab);

        System.out.println("\nplusGrdeSomme1(\"" + tabToStr(tab) + "\")\t : ");
        if (tab == null && res == null || exec[0] == res[0] && exec[1] == res[1]
                && exec[2] == res[2]) {
            System.out.println("OK");
        } else {
            System.out.println("ERREUR");
        }
    }

    /**
     * Test the function plusGrdeSomme1()
     * 
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    void testPlusGrandeSomme1() {
        System.out.println("\n******** " + new Object() {
        }.getClass().getEnclosingMethod().getName() + "()");
        System.out.println("\n****** cas normaux:");
        testCasplusGrdeSomme1(new int[] { 1, 2, 3 }, new int[] { 6, 0, 2 });
        testCasplusGrdeSomme1(new int[] { -1, 8, -4, 5, 6, -9, -7, 0, 12 },
                new int[] { 15, 1, 4 });
        testCasplusGrdeSomme1(new int[] { 1, 2, 3, -6, 2 }, new int[] { 6, 0, 2 });
        testCasplusGrdeSomme1(new int[] { 0, 0, 0, 0, 0 }, new int[] { 0, 0, 0 });
        System.out.println("\n**** cas limites:");
        testCasplusGrdeSomme1(new int[] { -6, -5, -9, -1, -7 }, new int[] { 0, 0, 0 });
        testCasplusGrdeSomme1(new int[] {}, new int[] { 0, 0, 0 });
        testCasplusGrdeSomme1(new int[] { 1 }, new int[] { 1, 0, 0 });
        System.out.println("\n** cas d'erreures:");
        testCasplusGrdeSomme1(null, null);
    }

    /**
     * Test the efficiency the method plusGrdeSomme1.
     * 
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    void testPlusGrdeSomme1Efficacite() {
        int[] tab = null;
        double eff = 0;
        long time = 0;
        long diff = 0;
        int n = 1000;

        System.out.println("\n**** " + new Object() {
        }.getClass().getEnclosingMethod().getName() + "()");
        for (int i = 1; i <= 10; i++) {
            cpt = 0;
            System.out.println("\n** n = " + n + ":");
            tab = new int[n];
            time = System.nanoTime();
            plusGrdeSomme1(tab);
            diff = (System.nanoTime() - time);
            System.out.println("Temps = " + diff + " ns");
            eff = cpt / Math.pow(n, 3);
            System.out.println("cpt/n^3 = " + eff + " constant = 0.16");
            n *= 2;
        }
    }

    /**
     * Find the greatest sum of an integer array.
     * 
     * @param tab The array of integer which we must find the greatest sum.
     * @return An array of integer in this format {sum, debut, end}, or null in case
     *         of error.
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    int[] plusGrdeSomme2(int tab[]) {
        int[] sum = (tab == null) ? null : new int[] { 0, 0, 0 };
        int buf = 0;

        if (sum != null && !TabIsNeg(tab)) {
            sum[0] = tab[0];
            for (int i = 0; i < tab.length; i++) {
                buf = 0;
                for (int j = i; j < tab.length; j++) {
                    buf += tab[j];
                    if (buf > sum[0]) {
                        sum[0] = buf;
                        sum[1] = i;
                        sum[2] = j;
                    }
                    cpt++;
                }
            }
        } else {
            System.err.println("Error: The integer array is null.");
        }
        return (sum);
    }

    /**
     * test a call of the Methode plusGrdeSomme2.
     * 
     * @param tab - An array of integer in this format {sum, debut, end}, or null in
     *            case of error.
     * @param res - The result expected.
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    void testCasplusGrdeSomme2(int[] tab, int[] res) {
        int[] exec = plusGrdeSomme2(tab);

        System.out.println("\nplusGrdeSomme2(\"" + tabToStr(tab) + "\")\t : ");
        if (tab == null && res == null || exec[0] == res[0] && exec[1] == res[1]
                && exec[2] == res[2]) {
            System.out.println("OK");
        } else {
            System.out.println("ERREUR");
        }
    }

    /**
     * Test the function plusGrdeSomme2()
     * 
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    void testPlusGrandeSomme2() {
        System.out.println("\n******** " + new Object() {
        }.getClass().getEnclosingMethod().getName() + "()");
        System.out.println("\n****** cas normaux:");
        testCasplusGrdeSomme2(new int[] { 1, 2, 3 }, new int[] { 6, 0, 2 });
        testCasplusGrdeSomme2(new int[] { -1, 8, -4, 5, 6, -9, -7, 0, 12 },
                new int[] { 15, 1, 4 });
        testCasplusGrdeSomme2(new int[] { 1, 2, 3, -6, 2 }, new int[] { 6, 0, 2 });
        testCasplusGrdeSomme2(new int[] { 0, 0, 0, 0, 0 }, new int[] { 0, 0, 0 });
        System.out.println("\n**** cas limites:");
        testCasplusGrdeSomme2(new int[] { -6, -5, -9, -1, -7 }, new int[] { 0, 0, 0 });
        testCasplusGrdeSomme2(new int[] {}, new int[] { 0, 0, 0 });
        testCasplusGrdeSomme2(new int[] { 1 }, new int[] { 1, 0, 0 });
        System.out.println("\n** cas d'erreures:");
        testCasplusGrdeSomme2(null, null);
    }

    /**
     * Test the efficiency the method plusGrdeSomme2.
     * 
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    void testPlusGrdeSomme2Efficacite() {
        int[] tab = null;
        double eff = 0;
        long time = 0;
        long diff = 0;
        int n = 1000;

        System.out.println("\n**** " + new Object() {
        }.getClass().getEnclosingMethod().getName() + "()");
        for (int i = 1; i <= 10; i++) {
            cpt = 0;
            System.out.println("\n** n = " + n + ":");
            tab = new int[n];
            time = System.nanoTime();
            plusGrdeSomme2(tab);
            diff = (System.nanoTime() - time);
            System.out.println("Temps = " + diff + " ns");
            eff = cpt / Math.pow(n, 2);
            System.out.println("cpt/n^2 = " + eff + " constant = 0.5");
            n *= 2;
        }
    }

    /**
     * Find the greatest sum of an integer array.
     * 
     * @param tab The array of integer which we must find the greatest sum.
     * @param deb An integer, the start of the interval.
     * @param end An integer, the end of the interval.
     * @return An array of integer in this format {sum, debut, end}, or null in case
     *         of error.
     * @author: Clément MONFORT.
     */
    int[] plusGrdeSomme3(int tab[], int deb, int end) {
        int mid = (deb + end) / 2;
        int[] sum = (tab == null) ? null : new int[] { 0, 0, 0 };
        int[] sum1 = null;
        int[] sum2 = null;
        int max = 0;
        int buf = 0;

        if (sum != null && end < tab.length && deb >= 0 && deb <= end
                && !TabIsNeg(tab)) {
            if (deb == end) {
                sum[0] = tab[end];
                sum[1] = deb;
                sum[2] = end;
            } else {
                sum[1] = mid;
                sum[2] = mid + 1;
                sum1 = plusGrdeSomme3(tab, deb, mid);
                buf = tab[mid];
                max = tab[mid];
                for (int i = mid - 1; i >= deb; i--) {
                    buf += tab[i];
                    if (buf > max) {
                        max = buf;
                        sum[1] = i;
                    }
                    cpt++;
                }
                sum[0] += max;
                sum2 = plusGrdeSomme3(tab, mid + 1, end);
                buf = tab[mid + 1];
                max = tab[mid + 1];
                for (int i = mid + 2; i <= end; i++) {
                    buf += tab[i];
                    if (buf > max) {
                        max = buf;
                        sum[2] = i;
                    }
                    cpt++;
                }
                sum[0] += max;
                if (sum1[0] >= sum2[0] && sum1[0] >= sum[0]) {
                    sum = sum1;
                } else if (sum2[0] >= sum1[0] && sum2[0] >= sum[0]) {
                    sum = sum2;
                }
            }
        }
        return (sum);
    }

    /**
     * test a call of the Methode plusGrdeSomme2.
     * 
     * @param tab - An array of integer in this format {sum, debut, end}, or null in
     *            case of error.
     * @param res - The result expected.
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    void testCasplusGrdeSomme3(int[] tab, int deb, int end, int[] res) {
        int[] exec = plusGrdeSomme3(tab, deb, end);

        System.out.println("\nplusGrdeSomme4(\"" + tabToStr(tab) + ", " + deb + ", "
                + end + "\")\t : " + tabToStr(res));
        if (tab == null && res == null || exec[0] == res[0] && exec[1] == res[1]
                && exec[2] == res[2]) {
            System.out.println("OK");
        } else {
            System.out.println("ERREUR");
        }
    }

    /**
     * Test the function plusGrdeSomme3()
     * 
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    void testPlusGrandeSomme3() {
        System.out.println("\n******** " + new Object() {
        }.getClass().getEnclosingMethod().getName() + "()");
        System.out.println("\n****** cas normaux:");
        testCasplusGrdeSomme3(new int[] { 1, 2, 3 }, 0, 2, new int[] { 6, 0, 2 });
        testCasplusGrdeSomme3(new int[] { -1, 8, -4, 5, 6, -9, -7, 0, 12 }, 0, 8,
                new int[] { 15, 1, 4 });
        testCasplusGrdeSomme3(new int[] { 1, 2, 3, -6, 2 }, 0, 4,
                new int[] { 6, 0, 2 });
        testCasplusGrdeSomme3(new int[] { 0, 0, 0, 0, 0 }, 0, 4, new int[] { 0, 0, 0 });
        System.out.println("\n**** cas limites:");
        testCasplusGrdeSomme3(new int[] { -6, -5, -9, -1, -7 }, 0, 4, new int[] { 0, 0, 0 });
        testCasplusGrdeSomme3(new int[] {}, 0, 0, new int[] { 0, 0, 0 });
        testCasplusGrdeSomme3(new int[] { 1 }, 0, 0, new int[] { 1, 0, 0 });
        System.out.println("\n** cas d'erreures:");
        testCasplusGrdeSomme3(new int[] { 1, 2, 3 }, -1, 2, new int[] { 0, 0, 0 });
        testCasplusGrdeSomme3(new int[] { 1, 2, 3 }, 2, 0, new int[] { 0, 0, 0 });
        testCasplusGrdeSomme3(null, 0, 24, null);
    }

    /**
     * Test the efficiency the method plusGrdeSomme3.
     * 
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    void testPlusGrdeSomme3Efficacite() {
        int[] tab = null;
        double eff = 0;
        long time = 0;
        long diff = 0;
        int n = 1000;

        System.out.println("\n**** " + new Object() {
        }.getClass().getEnclosingMethod().getName() + "()");
        for (int i = 1; i <= 10; i++) {
            cpt = 0;
            System.out.println("\n** n = " + n + ":");
            tab = new int[n];
            time = System.nanoTime();
            plusGrdeSomme3(tab, 0, n - 1);
            diff = (System.nanoTime() - time);
            System.out.println("Temps = " + diff + " ns");
            eff = cpt / (n * (Math.log10(n) / Math.log10(2)));
            System.out.println("cpt/nlog2n = " + eff + " constant = 1");
            n *= 2;
        }
    }

    /**
     * Find the greatest sum of an integer array.
     * 
     * @param tab The array of integer which we must find the greatest sum.
     * @return An array of integer in this format {sum, debut, end}, or null in case
     *         of error.
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    int[] plusGrdeSomme4(int tab[]) {
        int[] sum = (tab == null) ? null : new int[] { 0, 0, 0 };
        int buf = 0;
        int deb = 0;

        if (sum != null && !TabIsNeg(tab)) {
            sum[0] = tab[0];
            for (int i = 0; i < tab.length; i++) {
                buf += tab[i];
                if (buf > sum[0]) {
                    sum[0] = buf;
                    sum[1] = deb;
                    sum[2] = i;
                }
                if (buf < 0) {
                    deb = i + 1;
                    buf = 0;
                }
                cpt++;
            }
        } else {
            System.err.println("Error: The integer array is null.");
        }
        return (sum);
    }

    /**
     * test a call of the Methode plusGrdeSomme2.
     * 
     * @param tab - An array of integer in this format {sum, debut, end}, or null in
     *            case of error.
     * @param res - The result expected.
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    void testCasplusGrdeSomme4(int[] tab, int[] res) {
        int[] exec = plusGrdeSomme4(tab);

        System.out.println("\nplusGrdeSomme4(\"" + tabToStr(tab) + "\")\t : ");
        if (tab == null && res == null || exec[0] == res[0] && exec[1] == res[1]
                && exec[2] == res[2]) {
            System.out.println("OK");
        } else {
            System.out.println("ERREUR");
        }
    }

    /**
     * Test the function plusGrdeSomme4()
     * 
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    void testPlusGrandeSomme4() {
        System.out.println("\n******** " + new Object() {
        }.getClass().getEnclosingMethod().getName() + "()");
        System.out.println("\n****** cas normaux:");
        testCasplusGrdeSomme4(new int[] { 1, 2, 3 }, new int[] { 6, 0, 2 });
        testCasplusGrdeSomme4(new int[] { -1, 8, -4, 5, 6, -9, -7, 0, 12 },
                new int[] { 15, 1, 4 });
        testCasplusGrdeSomme4(new int[] { 1, 2, 3, -6, 2 }, new int[] { 6, 0, 2 });
        testCasplusGrdeSomme4(new int[] { 0, 0, 0, 0, 0 }, new int[] { 0, 0, 0 });
        System.out.println("\n**** cas limites:");
        testCasplusGrdeSomme4(new int[] { -6, -5, -9, -1, -7 }, new int[] { 0, 0, 0 });
        testCasplusGrdeSomme4(new int[] {}, new int[] { 0, 0, 0 });
        testCasplusGrdeSomme4(new int[] { 1 }, new int[] { 1, 0, 0 });
        System.out.println("\n** cas d'erreures:");
        testCasplusGrdeSomme4(null, null);
    }

    /**
     * Test the efficiency the method plusGrdeSomme4.
     * 
     * @author: Anthony ASCOET & Clément MONFORT.
     */
    void testPlusGrdeSomme4Efficacite() {
        int[] tab = null;
        double eff = 0;
        long time = 0;
        long diff = 0;
        int n = 1000;

        System.out.println("\n**** " + new Object() {
        }.getClass().getEnclosingMethod().getName() + "()");
        for (int i = 1; i <= 10; i++) {
            cpt = 0;
            System.out.println("\n** n = " + n + ":");
            tab = new int[n];
            time = System.nanoTime();
            plusGrdeSomme4(tab);
            diff = (System.nanoTime() - time);
            System.out.println("Temps = " + diff + " ns");
            eff = cpt / n;
            System.out.println("cpt/n = " + eff + " constant = 1");
            n *= 2;
        }
    }

    void principal() {
        testPlusGrandeSomme1();
        testPlusGrdeSomme1Efficacite();
        testPlusGrandeSomme2();
        testPlusGrdeSomme2Efficacite();
        testPlusGrandeSomme3();
        testPlusGrdeSomme3Efficacite();
        testPlusGrandeSomme4();
        testPlusGrdeSomme4Efficacite();
    }
}
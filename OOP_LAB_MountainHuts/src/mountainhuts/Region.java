package mountainhuts;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Class {@code Region} represents the main facade
 * class for the mountains hut system.
 * 
 * It allows defining and retrieving information about
 * municipalities and mountain huts.
 *
 */
public class Region {
	private  String name;
	private int[] altitudeRanges;
	private List <AltitudeRanges> arList = new LinkedList<>();
	private SortedMap <String,Municipality> munMap = new TreeMap<>();
	private SortedMap <String,MountainHut> mHutMap = new TreeMap<>();
	private static List <String> fileData = new LinkedList<>();
	/**
	 * Create a region with the given name.
	 * 
	 * @param name
	 *            the name of the region
	 */
	public Region(String name) {
       this.name=name;
	}

	/**
	 * Return the name of the region.
	 * 
	 * @return the name of the region
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Create the ranges given their textual representation in the format
	 * "[minValue]-[maxValue]".
	 * 
	 * @param ranges
	 *            an array of textual ranges
	 */
	public void setAltitudeRanges(String... ranges) {
		for(String actual: ranges) {
			AltitudeRanges temp= new AltitudeRanges(actual);
			arList.add(temp);
		}
	}

	/**
	 * Return the textual representation in the format "[minValue]-[maxValue]" of
	 * the range including the given altitude or return the default range "0-INF".
	 * 
	 * @param altitude
	 *            the geographical altitude
	 * @return a string representing the range
	 */
	public String getAltitudeRange(Integer altitude) {
		
		for(int i=0; i<arList.size();i++) {
			if(altitude>arList.get(i).getMinAltitude() && 
					altitude<=arList.get(i).getMaxAltitude())
				return arList.get(i).getInput();
			
		}
		
		return "0-INF"; //Default case
	}

	/**
	 * Create a new municipality if it is not already available or find it.
	 * Duplicates must be detected by comparing the municipality names.
	 * 
	 * @param name
	 *            the municipality name
	 * @param province
	 *            the municipality province
	 * @param altitude
	 *            the municipality altitude
	 * @return the municipality
	 */
	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
		if(munMap.get(name)==null) {
			Municipality temp=new Municipality(name,province,altitude);
			munMap.put(name, temp);
			return temp;
		}
		
		return munMap.get(name);
	}

	/**
	 * Return all the municipalities available.
	 * 
	 * @return a collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return munMap.values();
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 *
	 * @param name
	 *            the mountain hut name
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return the mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber,
			Municipality municipality) {
		if(mHutMap.get(name)==null) {
			MountainHut temp=new MountainHut(name,null,category,bedsNumber,municipality);
			mHutMap.put(name, temp);
			return temp;
		}		
		return mHutMap.get(name);
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 * 
	 * @param name
	 *            the mountain hut name
	 * @param altitude
	 *            the mountain hut altitude
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return a mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber,
			Municipality municipality) {
		if(mHutMap.get(name)==null) {
			MountainHut temp=new MountainHut(name,altitude,category,bedsNumber,municipality);
			mHutMap.put(name, temp);
			return temp;
		}		
		return mHutMap.get(name);
	}

	/**
	 * Return all the mountain huts available.
	 * 
	 * @return a collection of mountain huts
	 */
	public Collection<MountainHut> getMountainHuts() {
		return mHutMap.values();
	}

	/**
	 * Factory methods that creates a new region by loadomg its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"},
	 * <li>{@code "Municipality"},
	 * <li>{@code "MunicipalityAltitude"},
	 * <li>{@code "Name"},
	 * <li>{@code "Altitude"},
	 * <li>{@code "Category"},
	 * <li>{@code "BedsNumber"}
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
		fileData=readData(file);
		Region r = new Region(name);
		int cnt=0;
		String[] elaboration = new String[6];
		for(String e : fileData) {
			if(cnt!=0) {
				elaboration=e.split(";");
				r.createOrGetMunicipality(elaboration[1],elaboration[0],Integer.parseInt(elaboration[2]));
				if(elaboration[4].isEmpty())
				r.createOrGetMountainHut(elaboration[3], null,elaboration[5],
						Integer.valueOf(elaboration[6]),r.munMap.get(elaboration[1]));
				else
					r.createOrGetMountainHut(elaboration[3], Integer.valueOf(elaboration[4]),elaboration[5],
							Integer.valueOf(elaboration[6]),r.munMap.get(elaboration[1]));
					
					
			}
			cnt++;
		}
		
		return r;
	}

	/**
	 * Internal class that can be used to read the lines of
	 * a text file into a list of strings.
	 * 
	 * When reading a CSV file remember that the first line
	 * contains the headers, while the real data is contained
	 * in the following lines.
	 * 
	 * @param file the file name
	 * @return a list containing the lines of the file
	 */
	@SuppressWarnings("unused")
	private static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Count the number of municipalities with at least a mountain hut per each
	 * province.
	 * 
	 * @return a map with the province as key and the number of municipalities as
	 *         value
	 */
	public Map<String, Long> countMunicipalitiesPerProvince() {		
		return munMap.values().stream().collect(groupingBy(
				Municipality::getProvince,
				HashMap::new,
			    counting()
			 ));
	}

	/**
	 * Count the number of mountain huts per each municipality within each province.
	 * 
	 * @return a map with the province as key and, as value, a map with the
	 *         municipality as key and the number of mountain huts as value
	 */
	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		return mHutMap.values().stream().collect(groupingBy(
				e -> e.getMunicipality().getProvince(),
				HashMap::new,
				groupingBy(
						e -> e.getMunicipality().getName(),
						HashMap::new,
						counting()) //conta quanti mountainHUt hanno quel nome
				)
			);
	}

	/**
	 * Count the number of mountain huts per altitude range. If the altitude of the
	 * mountain hut is not available, use the altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the number of mountain huts
	 *         as value
	 */
	public Map<String, Long> countMountainHutsPerAltitudeRange() {	
		return mHutMap.values().stream().collect(groupingBy(
				m->this.getAltitudeRange(m.getAltitude().orElse(m.getMunicipality().getAltitude())),
				HashMap::new,
				counting()	
				));
	}

	/**
	 * Compute the total number of beds available in the mountain huts per each
	 * province.
	 * 
	 * @return a map with the province as key and the total number of beds as value
	 */
	public Map<String, Integer> totalBedsNumberPerProvince() {
		return mHutMap.values().stream().collect(groupingBy(
				m -> m.getMunicipality().getProvince(),
				HashMap::new,
				 summingInt(MountainHut::getBedsNumber)
				)
			  );
	}

	/**
	 * Compute the maximum number of beds available in a single mountain hut per
	 * altitude range. If the altitude of the mountain hut is not available, use the
	 * altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the maximum number of beds
	 *         as value
	 */
	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		 return mHutMap.values().stream().collect(groupingBy(
				m->this.getAltitudeRange(m.getAltitude().orElse(m.getMunicipality().getAltitude())),
				mapping(MountainHut::getBedsNumber,maxBy(Comparator.naturalOrder()))
				 
				)
			  );
	}

	/**
	 * Compute the municipality names per number of mountain huts in a municipality.
	 * The lists of municipality names must be in alphabetical order.
	 * 
	 * @return a map with the number of mountain huts in a municipality as key and a
	 *         list of municipality names as value
	 */
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
//		return mHutMap.values().collect(groupingBy(
//				totalBedsNumberPerProvince().values().iterator().next(),
//				mapping( MountainHut::getName,Comparator.naturalOrder())
//				));
		return null;
	}

}

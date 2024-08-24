package shahamat.hospitalmanagementsystem.secretary;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shahamat.hospitalmanagementsystem.doctor.Doctor;
import shahamat.hospitalmanagementsystem.doctor.DoctorRepository;
import shahamat.hospitalmanagementsystem.exception.CustomException;
import shahamat.hospitalmanagementsystem.hospital.Hospital;
import shahamat.hospitalmanagementsystem.hospital.HospitalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecretaryService {

    private final SecretaryRepository secretaryRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;

    // Convert DTO to Entity
    private Secretary toEntity(SecretaryDTO dto) {
        Secretary secretary = new Secretary();
        secretary.setFirstName(dto.getFirstName());
        secretary.setLastName(dto.getLastName());
        secretary.setPhone(dto.getPhone());
        secretary.setEmail(dto.getEmail());
        secretary.setYearsOfExperience(dto.getYearsOfExperience());

        if (dto.getDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new CustomException("Doctor not found", HttpStatus.NOT_FOUND, 2006));
            secretary.setDoctor(doctor);
        }

        if (dto.getHospitalId() != null) {
            Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                    .orElseThrow(() -> new CustomException("Hospital not found", HttpStatus.NOT_FOUND, 2004));
            secretary.setHospital(hospital);
        }

        return secretary;
    }

    // Convert Entity to DTO
    private SecretaryDTO toDTO(Secretary secretary) {
        SecretaryDTO dto = new SecretaryDTO();
        dto.setId(secretary.getId());
        dto.setFirstName(secretary.getFirstName());
        dto.setLastName(secretary.getLastName());
        dto.setPhone(secretary.getPhone());
        dto.setEmail(secretary.getEmail());
        dto.setYearsOfExperience(secretary.getYearsOfExperience());

        if (secretary.getDoctor() != null) {
            dto.setDoctorId(secretary.getDoctor().getId());
        }

        if (secretary.getHospital() != null) {
            dto.setHospitalId(secretary.getHospital().getId());
        }

        return dto;
    }

    // Save or update a secretary
    public SecretaryDTO save(SecretaryDTO dto) {
        Secretary secretary = toEntity(dto);
        Secretary savedSecretary = secretaryRepository.save(secretary);
        return toDTO(savedSecretary);
    }

    // Find secretary by ID
    public SecretaryDTO findById(Long id) {
        Secretary secretary = secretaryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Secretary not found", HttpStatus.NOT_FOUND, 2008));
        return toDTO(secretary);
    }

    // Find all secretaries
    public List<SecretaryDTO> findAll() {
        return secretaryRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Find secretaries by name
    public List<SecretaryDTO> findByName(String name) {
        return secretaryRepository.findByFirstName(name).stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Find secretaries by experience (years)
    public List<SecretaryDTO> findByYearsOfExperience(int years) {
        return secretaryRepository.findByYearsOfExperienceGreaterThanEqual(years)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Find secretaries by hospital ID
    public List<SecretaryDTO> findByHospitalId(Long hospitalId) {
        return secretaryRepository.findByHospital_Id(hospitalId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Delete secretary by ID
    public void deleteById(Long id) {
        if (secretaryRepository.existsById(id)) {
            secretaryRepository.deleteById(id);
        } else {
            throw new CustomException("Secretary not found", HttpStatus.NOT_FOUND, 2008);
        }
    }

    // Associate secretary with a hospital
    public SecretaryDTO addSecretaryToHospital(Long secretaryId, Long hospitalId) {
        Secretary secretary = secretaryRepository.findById(secretaryId)
                .orElseThrow(() -> new CustomException("Secretary not found", HttpStatus.NOT_FOUND, 2008));
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new CustomException("Hospital not found", HttpStatus.NOT_FOUND, 2004));
        secretary.setHospital(hospital);
        return toDTO(secretaryRepository.save(secretary));
    }

    // Associate secretary with a doctor
    public SecretaryDTO addSecretaryToDoctor(Long secretaryId, Long doctorId) {
        Secretary secretary = secretaryRepository.findById(secretaryId)
                .orElseThrow(() -> new CustomException("Secretary not found", HttpStatus.NOT_FOUND, 2008));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new CustomException("Doctor not found", HttpStatus.NOT_FOUND, 2006));
        secretary.setDoctor(doctor);
        return toDTO(secretaryRepository.save(secretary));
    }

    // Remove secretary from a hospital
    public SecretaryDTO removeSecretaryFromHospital(Long secretaryId, Long hospitalId) {
        Secretary secretary = secretaryRepository.findById(secretaryId)
                .orElseThrow(() -> new CustomException("Secretary not found", HttpStatus.NOT_FOUND, 2008));
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new CustomException("Hospital not found", HttpStatus.NOT_FOUND, 2004));
        if (secretary.getHospital().equals(hospital)) {
            secretary.setHospital(null);
            return toDTO(secretaryRepository.save(secretary));
        }
        throw new CustomException("Secretary not associated with this hospital", HttpStatus.BAD_REQUEST, 2010);
    }

    // Remove doctor from secretary's list
    public SecretaryDTO removeDoctorFromSecretary(Long secretaryId, Long doctorId) {
        Secretary secretary = secretaryRepository.findById(secretaryId)
                .orElseThrow(() -> new CustomException("Secretary not found", HttpStatus.NOT_FOUND, 2008));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new CustomException("Doctor not found", HttpStatus.NOT_FOUND, 2006));
        secretary.setDoctor(null);
        return toDTO(secretaryRepository.save(secretary));
    }
}

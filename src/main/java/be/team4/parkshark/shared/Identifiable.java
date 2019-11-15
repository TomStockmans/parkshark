package be.team4.parkshark.shared;

public interface Identifiable<ID extends UniqueId> {

    ID getId();

}

package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

public class PetSingleton {
    private static PetSingleton petInstance;
    public List<Pet> petList = new ArrayList<Pet>();

    private PetSingleton(){
        Pet pet1 = new Pet();
        pet1.setPetId(1);
        pet1.setPetAge(3);
        pet1.setPetName("Boola");
        pet1.setPetType("Dog");

        Pet pet2 = new Pet();
        pet2.setPetId(2);
        pet2.setPetAge(4);
        pet2.setPetName("Sudda");
        pet2.setPetType("Cat");

        Pet pet3 = new Pet();
        pet3.setPetId(3);
        pet3.setPetAge(2);
        pet3.setPetName("Peththappu");
        pet3.setPetType("Bird");

        petList.add(pet1);
        petList.add(pet2);
        petList.add(pet3);
    }
    public static PetSingleton getInstance()
    {
        if(petInstance == null)
            petInstance = new PetSingleton();
        return petInstance;
    }
    public List<Pet> createNewPet(Pet pet)
    {
        petList.add(pet);
        return petList;
    }
    public List<Pet> getPetList()
    {
        return petList;
    }
    public Pet getPetById(int id)
    {
        for (Pet pet:petList)
        {
            if(pet.getPetId() == id)
            {
                return pet;
            }
        }
        return null;
    }
    public List<Pet> updatePet(Pet pet)
    {
        for (Pet pets:petList)
        {
            if(pets.getPetId() == pet.getPetId())
            {
                pets.setPetType(pet.getPetType());
                pets.setPetName(pet.getPetName());
                pets.setPetAge(pet.getPetAge());
                return petList;
            }
        }
        return null;
    }
    public List<Pet> deletePet(int id)
    {
        for (Pet pet:petList)
        {
            if(pet.getPetId() == id)
            {
                petList.remove(pet);
                return petList;
            }
        }
        return null;
    }

}

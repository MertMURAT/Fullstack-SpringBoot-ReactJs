"use client"
import { Template, ImageCard } from "@/components";
import { Image } from "@/resources/image/image.resources";
import { useImageService } from "@/resources/image/image.service";
import { useState } from "react";

export default function GalleryPages() {
    const [images, setImages] = useState<Image[]>([]);
    const userService = useImageService();

    async function searchImages() {
        const result = await userService.search();
        setImages(result);
        console.table(result);
        console.table("IMAGES : " + images);
    }

    function renderImageCard(image: Image) {
        return (
            <div>
                <ImageCard
                    src={image.url ? image.url : "https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg"}
                    name={image.name}
                    size={image.size}
                    dataUpload={image.uploadDate} />
            </div>
        );
    }

    function renderImageCards() {
        // return images.map(image => renderImageCard(image));
        return images.map(renderImageCard);
    }

    return (
        <Template>
            <button className="bg-gray-500" onClick={searchImages} >SEARCH</button>
            <h4 className="font-bold text-3xl py-2">Gallery</h4>
            <section className="grid grid-cols-4 gap-8">
                {
                    renderImageCards()
                }


            </section>
        </Template>
    );
}